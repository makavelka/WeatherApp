package com.example.weatherapp.model;

import com.example.weatherapp.Const;
import com.example.weatherapp.di.App;
import com.example.weatherapp.model.api.ApiInterface;
import com.example.weatherapp.model.pojo.flickr.Flickr;
import com.example.weatherapp.model.pojo.weather.CurrentWeather;
import com.example.weatherapp.model.pojo.weather.FiveDaysWeather;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class ModelImpl implements Model {

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public void getDataFromDb() {

    }

    @Override
    public Observable<CurrentWeather> getCurrentWeather(String city) {
        return apiInterface.getWeather(city)
                .subscribeOn(ioThread)
                .observeOn(uiThread);
    }

    @Override
    public Observable<FiveDaysWeather> getFiveDaysWeather(String city) {
        return apiInterface.getWeatherForFiveDays(city)
                .subscribeOn(ioThread)
                .observeOn(uiThread);
    }

    @Override
    public void getData() {

    }

    @Override
    public boolean checkIfDataExistInDb() {
        return false;
    }

    @Override
    public Observable<Flickr> getImages(double lat, double lon) {
        return apiInterface.getImages(Const.FLICKR_URL, 1, 1, lat, lon)
                .subscribeOn(ioThread)
                .observeOn(uiThread);
    }
}
