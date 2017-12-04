package com.example.android.sunshine.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.WeatherEntry;

import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by penglong on 2017/11/29.
 */

public class DetailActivityViewModel extends ViewModel {

    private final LiveData<WeatherEntry> mWeather;

    private final Date mDate;
    private final SunshineRepository mRepository;

    public DetailActivityViewModel(SunshineRepository repository, Date date) {
        mRepository = repository;
        mDate = date;
        mWeather = mRepository.getWeatherByDate(mDate);
    }

    public LiveData<WeatherEntry> getWeather() {
        return mWeather;
    }

}
