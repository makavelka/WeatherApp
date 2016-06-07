package com.example.weatherapp.model;

import com.example.weatherapp.model.pojo.flickr.Flickr;
import com.example.weatherapp.model.pojo.weather.CurrentWeather;
import com.example.weatherapp.model.pojo.weather.FiveDaysWeather;
import com.example.weatherapp.model.pojo.weather.SimpleCurrentWeather;

import rx.Observable;

public interface Model {
    void getDataFromDb();
    Observable<CurrentWeather> getCurrentWeather(String city);
    Observable<FiveDaysWeather> getFiveDaysWeather(String city);
    SimpleCurrentWeather getData(String city);
    boolean checkIfDataExistInDb();
    Observable<Flickr> getImages(double lat, double lon);
    void saveToDb(SimpleCurrentWeather simpleCurrentWeather);
    SimpleCurrentWeather getLastWeather();
    SimpleCurrentWeather getLastWeatherByCity(String city);
}
