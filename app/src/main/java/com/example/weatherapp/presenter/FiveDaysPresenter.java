package com.example.weatherapp.presenter;

import com.example.weatherapp.model.pojo.weather.FiveDaysWeather;

public interface FiveDaysPresenter extends Presenter {
    void getData(String city);
    void parseData(FiveDaysWeather fiveDaysWeather);
    String getCity(double lat, double lon);
}
