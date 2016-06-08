package com.example.weatherapp.di;

import com.example.weatherapp.Utils;
import com.example.weatherapp.di.module.ApiModule;
import com.example.weatherapp.di.module.AppModule;
import com.example.weatherapp.di.module.ModelModule;
import com.example.weatherapp.di.module.PresenterModule;
import com.example.weatherapp.di.module.UtilsModule;
import com.example.weatherapp.di.module.ViewModule;
import com.example.weatherapp.model.ModelImpl;
import com.example.weatherapp.model.api.ApiService;
import com.example.weatherapp.model.api.GoogleApi;
import com.example.weatherapp.model.api.HttpClient;
import com.example.weatherapp.model.api.PicassoService;
import com.example.weatherapp.model.api.ResponseCacheInterceptor;
import com.example.weatherapp.model.db.FiveDaysWeatherDBDataSource;
import com.example.weatherapp.model.db.FiveDaysWeatherDBHelper;
import com.example.weatherapp.model.db.WeatherDBDataSource;
import com.example.weatherapp.model.db.WeatherDBHelper;
import com.example.weatherapp.presenter.CurrentPresenterImpl;
import com.example.weatherapp.presenter.FiveDaysPresenterImpl;
import com.example.weatherapp.presenter.MainPresenterImpl;
import com.example.weatherapp.view.MainActivity;
import com.example.weatherapp.view.fragment.CurrentWeatherFragment;
import com.example.weatherapp.view.fragment.FiveDaysFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Компонент для DI, который предоставляет данные в классы, заключенные в конструкцию void inject(ClassName.class);
 * Предоставляемые данные берутся из модулей, включенных в компонент в конструкции modules = {Class<T>...}
 */

@Singleton
@Component(modules = {AppModule.class, UtilsModule.class, PresenterModule.class, ApiModule.class, ViewModule.class, ModelModule.class})
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(Utils utils);

    void inject(ResponseCacheInterceptor interceptor);

    void inject(ApiService api);

    void inject(HttpClient client);

    void inject(PicassoService picassoService);

    void inject(ModelImpl model);

    void inject(CurrentWeatherFragment fragment);

    void inject(FiveDaysFragment fragment);

    void inject(CurrentPresenterImpl presenter);

    void inject(FiveDaysPresenterImpl presenter);

    void inject(GoogleApi googleApi);

    void inject(MainPresenterImpl presenter);

    void inject(FiveDaysWeatherDBHelper helper);

    void inject (WeatherDBHelper helper);

    void inject (FiveDaysWeatherDBDataSource dbDataSource);

    void inject (WeatherDBDataSource dbDataSource);

}
