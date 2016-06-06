package com.example.weatherapp.view;

import com.example.weatherapp.model.pojo.weather.SimpleWeather;

import java.util.List;

public interface FiveDaysWeatherView extends IView {
    void showWeather(List<SimpleWeather> simpleWeathers);
    void showNoData();
    void showError(String error);
}
