package com.example.android.sunshine.ui.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.sunshine.data.SunshineRepository;

/**
 * Created by penglong on 2017/12/4.
 */

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final SunshineRepository mRepository;

    public MainViewModelFactory(SunshineRepository mRepository) {
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(mRepository);
    }
}
