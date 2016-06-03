package com.example.weatherapp.di.module;

import com.example.weatherapp.presenter.CurrentPresenterImpl;
import com.example.weatherapp.presenter.FiveDaysPresenterImpl;
import com.example.weatherapp.presenter.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    MainPresenterImpl provideMainPresenter() {
        return new MainPresenterImpl();
    }

    @Provides
    CurrentPresenterImpl provideCurrentPresenter() {
        return new CurrentPresenterImpl();
    }

    @Provides
    FiveDaysPresenterImpl provideFiveDaysPresenter() {
        return new FiveDaysPresenterImpl();
    }
}
