package com.example.weatherapp.di.module;

import com.example.weatherapp.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    Utils provideUtils() {
        return new Utils();
    }
}