package com.example.weatherapp.di;

import android.app.Application;

import com.example.weatherapp.di.module.AppModule;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    /**
     * Создание графа зависимостей DI при старте приложения
     */
    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    /**
     * Метод собирающий граф зависимостей для DI.
     * Вызывается в классе Application для доступа по всему приложению.
     * @return граф зависимостей DI
     */
    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(App.this))
                .build();
    }
}
