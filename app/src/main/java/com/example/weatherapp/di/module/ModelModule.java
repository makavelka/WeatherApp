package com.example.weatherapp.di.module;


import android.content.Context;

import com.example.weatherapp.Const;
import com.example.weatherapp.model.api.ApiInterface;
import com.example.weatherapp.model.api.ApiService;
import com.example.weatherapp.model.db.FiveDaysWeatherDBDataSource;
import com.example.weatherapp.model.db.FiveDaysWeatherDBHelper;
import com.example.weatherapp.model.db.WeatherDBDataSource;
import com.example.weatherapp.model.db.WeatherDBHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ModelModule {

    /**
     * Предоставляет шедулер для Rx, который будет работать в главном потоке.
     * Используется для вывода полученных данных на экран.
     *
     * @return шедулер главного потока.
     */
    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    /**
     * Предоставляет шедулер для Rx, который будет работать с данными в фоне.
     * Используется для получения данных.
     *
     * @return шедулер для фоновой работы.
     */
    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }


    /**
     * Предоставляет интерфейс, который запрашивает данные по REST.
     *
     * @return интерфейс для получения данных.
     */
    @Provides
    @Singleton
    ApiInterface provideApiService() {
        return new ApiService().getApiService();
    }

    @Provides
    @Singleton
    WeatherDBDataSource providesWeatherDb() {
        return new WeatherDBDataSource();
    }

    @Provides
    @Singleton
    FiveDaysWeatherDBDataSource providesFiveDaysWeatherDb() {
        return new FiveDaysWeatherDBDataSource();
    }

    @Provides
    @Singleton
    FiveDaysWeatherDBHelper providesFiveDaysHelper(Context context) {
        return new FiveDaysWeatherDBHelper(context);
    }

    @Provides
    @Singleton
    WeatherDBHelper providesWeatherHelper(Context context) {
        return new WeatherDBHelper(context);
    }

}