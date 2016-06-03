package com.example.weatherapp.model;

import com.example.weatherapp.model.pojo.CurrentWeather;
import com.example.weatherapp.model.pojo.FiveDaysWeather;

import rx.Observable;

public interface Model {
    void getDataFromDb();
    Observable<CurrentWeather> getCurrentWeather(String city);
    Observable<FiveDaysWeather> getFiveDaysWeather(String city);
    void getData();
    boolean checkIfDataExistInDb();
}
