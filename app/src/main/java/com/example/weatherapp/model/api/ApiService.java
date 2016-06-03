package com.example.weatherapp.model.api;

import com.example.weatherapp.Const;
import com.example.weatherapp.di.App;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Класс, используемый для настройки Retrofit, и реализующий интерфейсы для получения данных
 */
public class ApiService {

    //Инъекция контекста, необходим для инициализации кэща
    @Inject
    OkHttpClient mHttpClient;

    //Вызов метода inject в конструкторе класса, чтобы инъектируемые данные были доступны сразу
    public ApiService() {
        App.getComponent().inject(this);
    }


    public ApiInterface getApiService() {
        //Создание экземпляра Retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                //Установка базового url
                .baseUrl(Const.BASE_URL)
                //Установка стандартного клиента
                .client(mHttpClient)
                //Установка фабрики, которая будет конвертировать полученные данные в POJO
                .addConverterFactory(GsonConverterFactory.create())
                //Установка фабрики, для работы с RxJava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
