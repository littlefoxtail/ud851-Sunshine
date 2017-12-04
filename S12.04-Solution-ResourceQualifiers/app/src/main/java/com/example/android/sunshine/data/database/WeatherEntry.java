package com.example.android.sunshine.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by penglong on 2017/11/29.
 */

@Entity(tableName = "weather", indices = {@Index(value = "date", unique = true)})
public class WeatherEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int weatherlconld;
    private Date date;
    private double min;
    private double max;
    private double humidity;
    private double pressure;
    private double wind;
    private double degrees;

    public WeatherEntry(int id, int weatherlconld, Date date, double min, double max, double humidity, double pressure, double wind, double degrees) {
        this.id = id;
        this.weatherlconld = weatherlconld;
        this.date = date;
        this.min = min;
        this.max = max;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
        this.degrees = degrees;
    }

    @Ignore
    public WeatherEntry(int weatherlconld, Date date, double min, double max, double humidity, double pressure, double wind, double degrees) {
        this.weatherlconld = weatherlconld;
        this.date = date;
        this.min = min;
        this.max = max;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
        this.degrees = degrees;
    }

    public int getId() {
        return id;
    }

    public int getWeatherlconld() {
        return weatherlconld;
    }

    public Date getDate() {
        return date;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getWind() {
        return wind;
    }

    public double getDegrees() {
        return degrees;
    }
}
