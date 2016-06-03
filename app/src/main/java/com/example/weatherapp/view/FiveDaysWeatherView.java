package com.example.weatherapp.view;

import com.example.weatherapp.model.pojo.FiveDaysWeather;

import java.util.List;

public interface FiveDaysWeatherView extends IView {
    void showWeather(List<FiveDaysWeather> list);
    void showNoData();
    void showError(String error);
}
