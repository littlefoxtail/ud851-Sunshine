package com.example.android.sunshine.ui.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.sunshine.data.SunshineRepository;

import java.util.Date;

/**
 * Created by penglong on 2017/12/3.
 */

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SunshineRepository mRepository;

    private final Date mDate;


    public DetailViewModelFactory(SunshineRepository mRepository, Date mDate) {
        this.mRepository = mRepository;
        this.mDate = mDate;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailActivityViewModel(mRepository, mDate);
    }
}
