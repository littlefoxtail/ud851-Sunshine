package com.example.android.sunshine.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.WeatherEntry;

import java.util.List;

/**
 * Created by penglong on 2017/12/4.
 */

public class MainActivityViewModel extends ViewModel {
    private final SunshineRepository mRepository;

    private final LiveData<List<WeatherEntry>> mForecast;

    public MainActivityViewModel(SunshineRepository mRepository) {
        this.mRepository = mRepository;
        this.mForecast = mRepository.getCurrentWeatherForecasts();
    }

    public LiveData<List<WeatherEntry>> getForecast() {
        return mForecast;
    }
}
