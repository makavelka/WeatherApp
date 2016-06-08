package com.example.weatherapp.presenter;

public interface CurrentPresenter extends Presenter{
    void getData(String city);
    String getCity(double lat, double lon);
}
