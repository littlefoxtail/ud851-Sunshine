package com.example.android.sunshine.utilities;

import android.content.Context;
import android.support.v4.content.res.FontResourcesParserCompat;

import com.example.android.sunshine.AppExecutors;
import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.SunshineDatabase;
import com.example.android.sunshine.data.network.WeatherNetworkDataSource;
import com.example.android.sunshine.ui.detail.DetailViewModelFactory;
import com.example.android.sunshine.ui.list.MainViewModelFactory;

import java.util.Date;

/**
 * Created by penglong on 2017/12/1.
 */

public class InjectorUtils {

    public static SunshineRepository provideRepository(Context context) {
        SunshineDatabase database = SunshineDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();

        WeatherNetworkDataSource networkDataSource = WeatherNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return SunshineRepository.getInstance(database.weatherDao(), networkDataSource, executors);
    }

    public static WeatherNetworkDataSource provideNetworkDataSource(Context context) {
        provideRepository(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return WeatherNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    public static DetailViewModelFactory provideDetailViewModelFactory(Context context, Date date) {
        SunshineRepository repository = provideRepository(context.getApplicationContext());
        return new DetailViewModelFactory(repository, date);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        SunshineRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }
}
