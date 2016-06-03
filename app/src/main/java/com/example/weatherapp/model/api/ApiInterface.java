package com.example.weatherapp.model.api;


import com.example.weatherapp.model.pojo.flickr.Flickr;
import com.example.weatherapp.model.pojo.weather.CurrentWeather;
import com.example.weatherapp.model.pojo.weather.FiveDaysWeather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Интерфейс, используемый библиотекой Retrofit, для создания REST запросов на их основе
 */
public interface ApiInterface {

    @GET("weather?units=metric&apikey=8278cb9d20902f4ec47ce3eec5dffdd1")
    Observable<CurrentWeather> getWeather(@Query("q") String city);

    @GET("forecast?units=metric&apikey=8278cb9d20902f4ec47ce3eec5dffdd1")
    Observable<FiveDaysWeather> getWeatherForFiveDays(@Query("q") String city);

    @GET("services/rest/?method=flickr.photos.search&api_key=49dad1ba5c2a832dba3c1ace307eeb56" +
            "&format=json&" +
            "nojsoncallback=1" +
            "&extras=url_m")
    Observable<Flickr> getImages(@Query("text") String query, @Query("page") int page, @Query("per_page") int perPage);
}
