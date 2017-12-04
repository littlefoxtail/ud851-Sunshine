package com.example.android.sunshine.data.network;

import android.support.annotation.NonNull;

import com.example.android.sunshine.data.database.WeatherEntry;

/**
 * Created by penglong on 2017/11/30.
 */

public class WeatherResponse {

    @NonNull
    private final WeatherEntry[] mWeatherForecast;

    public WeatherResponse(@NonNull final WeatherEntry[] weatherForecast) {
        mWeatherForecast = weatherForecast;
    }

    public WeatherEntry[] getWeatherForecast() {
        return mWeatherForecast;
    }
}
