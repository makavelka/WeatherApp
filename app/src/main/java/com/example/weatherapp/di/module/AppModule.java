package com.example.weatherapp.di.module;

import android.content.Context;

import com.example.weatherapp.di.App;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль, предоставляющий контекст приложения
 */

@Module
public class AppModule {

    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }

    @Provides
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
