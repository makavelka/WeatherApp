package com.example.weatherapp.view;

import com.example.weatherapp.model.pojo.weather.CurrentWeather;
import com.example.weatherapp.model.pojo.weather.SimpleCurrentWeather;

public interface CurrentWeatherView extends IView {
    void showWeather(CurrentWeather weather);
    void showWeather(SimpleCurrentWeather weather);
    void showNoData();
    void showError(String error);
}
