package com.example.weatherapp.model.api;


import com.example.weatherapp.model.pojo.Weather;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Интерфейс, используемый библиотекой Retrofit, для создания REST запросов на их основе
 */
public interface ApiInterface {

    @GET("")
    Observable<Weather> getWeather();
}
