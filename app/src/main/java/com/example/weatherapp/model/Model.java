package com.example.weatherapp.model;

import com.example.weatherapp.model.pojo.flickr.Flickr;
import com.example.weatherapp.model.pojo.weather.CurrentWeather;
import com.example.weatherapp.model.pojo.weather.FiveDaysWeather;

import rx.Observable;

public interface Model {
    void getDataFromDb();
    Observable<CurrentWeather> getCurrentWeather(String city);
    Observable<FiveDaysWeather> getFiveDaysWeather(String city);
    void getData();
    boolean checkIfDataExistInDb();
    Observable<Flickr> getImages(double lat, double lon);
}
