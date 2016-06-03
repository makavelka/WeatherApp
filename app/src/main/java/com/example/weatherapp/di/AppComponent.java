package com.example.weatherapp.di;

import com.example.weatherapp.Utils;
import com.example.weatherapp.di.module.ApiModule;
import com.example.weatherapp.di.module.AppModule;
import com.example.weatherapp.di.module.PresenterModule;
import com.example.weatherapp.di.module.UtilsModule;
import com.example.weatherapp.model.api.ApiService;
import com.example.weatherapp.model.api.HttpClient;
import com.example.weatherapp.model.api.PicassoService;
import com.example.weatherapp.model.api.ResponseCacheInterceptor;
import com.example.weatherapp.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Компонент для DI, который предоставляет данные в классы, заключенные в конструкцию void inject(ClassName.class);
 * Предоставляемые данные берутся из модулей, включенных в компонент в конструкции modules = {Class<T>...}
 */

@Singleton
@Component(modules = {AppModule.class, UtilsModule.class, PresenterModule.class, ApiModule.class})
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(Utils utils);

    void inject(ResponseCacheInterceptor interceptor);

    void inject(ApiService api);

    void inject(HttpClient client);

    void inject(PicassoService picassoService);


}