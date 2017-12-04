package com.example.android.sunshine.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.android.sunshine.AppExecutors;
import com.example.android.sunshine.data.database.DateConverter;
import com.example.android.sunshine.data.database.ListWeatherEntry;
import com.example.android.sunshine.data.database.WeatherDao;
import com.example.android.sunshine.data.database.WeatherEntry;
import com.example.android.sunshine.data.network.WeatherNetworkDataSource;
import com.example.android.sunshine.utilities.SunshineDateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by penglong on 2017/11/30.
 */

public class SunshineRepository {
    private static final String TAG = "SunshineRepository";
    private static SunshineRepository instance;
    private static final Object LOCK = new Object();
    private final WeatherDao weatherDao;
    private final WeatherNetworkDataSource networkDataSource;
    private final AppExecutors executors;
    private boolean mInitialized = false;

    private SunshineRepository(WeatherDao weatherDao,
                               WeatherNetworkDataSource networkDataSource,
                               AppExecutors executors) {
        this.weatherDao = weatherDao;
        this.networkDataSource = networkDataSource;
        this.executors = executors;

        LiveData<WeatherEntry[]> networkData = networkDataSource.getCurrentWeatherForecasts();
        networkData.observeForever(newForecastsFromNetwork -> {
            executors.diskIO().execute(() -> {
                deleteOldData();
                Log.d(TAG, "Old weather deleted");
                weatherDao.bulkInsert(newForecastsFromNetwork);
                Log.d(TAG, "New values inserted");
            });
        });
    }

    public static SunshineRepository getInstance(WeatherDao weatherDao,
                                                 WeatherNetworkDataSource networkDataSource,
                                                 AppExecutors executors) {
        Log.d(TAG, "Getting the repository");
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new SunshineRepository(weatherDao, networkDataSource, executors);
                }
            }
        }
        return instance;
    }

    public synchronized void initializeData() {
        if (mInitialized) return;
        mInitialized = true;

        networkDataSource.scheduleRecurringFetchWeatherSync();
        executors.diskIO().execute(() -> {
            if (isFetchNeeded()) {
                startFetchWeatherService();
            }
        });

    }

    public LiveData<List<ListWeatherEntry>> getCurrentWeatherForecasts() {
        initializeData();
        Date today = SunshineDateUtils.getNormalizedUtcDateForToday();
        return weatherDao.getCurrentWeatherForecasts(today);
    }

    private void startFetchWeatherService() {
        networkDataSource.startFetchWeatherService();
    }

    private void deleteOldData() {
        Date today = SunshineDateUtils.getNormalizedUtcDateForToday();
        weatherDao.deleteOldWeather(today);
    }

    private boolean isFetchNeeded() {
        Date today = SunshineDateUtils.getNormalizedUtcDateForToday();
        int count = weatherDao.countAllFutureWeather(today);
        return count < WeatherNetworkDataSource.NUM_DAYS;
    }


    public LiveData<WeatherEntry> getWeatherByDate(Date date) {
        initializeData();
        return weatherDao.getWeatherByDate(date);
    }
}
