package com.example.weatherapp.view;

import com.example.weatherapp.model.pojo.weather.CurrentWeather;

public interface CurrentWeatherView extends IView {
    void showWeather(CurrentWeather weather);
    void showNoData();
    void showError(String error);
    void showBackground(String url);
}
