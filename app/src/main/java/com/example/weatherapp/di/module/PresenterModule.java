package com.example.weatherapp.di.module;

import com.example.weatherapp.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class PresenterModule {

    /**
     * Предоставляет экземпляр класса, использущегося для получения данных со слоя Model
     * @return
     */
    @Provides
    @Singleton
    ModelImpl provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
