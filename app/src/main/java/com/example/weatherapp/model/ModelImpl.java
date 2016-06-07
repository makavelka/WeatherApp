package com.example.weatherapp.model;

import com.example.weatherapp.Const;
import com.example.weatherapp.di.App;
import com.example.weatherapp.model.api.ApiInterface;
import com.example.weatherapp.model.db.WeatherDBDataSource;
import com.example.weatherapp.model.pojo.flickr.Flickr;
import com.example.weatherapp.model.pojo.weather.CurrentWeather;
import com.example.weatherapp.model.pojo.weather.FiveDaysWeather;
import com.example.weatherapp.model.pojo.weather.SimpleCurrentWeather;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class ModelImpl implements Model {

    @Inject
    protected ApiInterface apiInterface;
    @Inject
    WeatherDBDataSource mDbDataSource;

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
    public SimpleCurrentWeather getData(String city) {
        if (checkIfDataExistInDb()) {
            return getLastWeatherByCity(city);
        } else {
            return null;
        }
    }

    @Override
    public boolean checkIfDataExistInDb() {
        return getLastWeather() != null;
    }

    @Override
    public Observable<Flickr> getImages(double lat, double lon) {
        return apiInterface.getImages(Const.FLICKR_URL, 1, 1, lat, lon)
                .subscribeOn(ioThread)
                .observeOn(uiThread);
    }

    @Override
    public void saveToDb(SimpleCurrentWeather simpleCurrentWeather) {
        mDbDataSource.open();
        mDbDataSource.addWeather(simpleCurrentWeather);
        mDbDataSource.close();
    }

    @Override
    public SimpleCurrentWeather getLastWeather() {
        mDbDataSource.open();
        SimpleCurrentWeather simpleCurrentWeather = mDbDataSource.getLastWeather();
        mDbDataSource.close();
        return simpleCurrentWeather;
    }

    @Override
    public SimpleCurrentWeather getLastWeatherByCity(String city) {
        mDbDataSource.open();
        SimpleCurrentWeather simpleCurrentWeather = mDbDataSource.getLastWeatherByCity(city);
        mDbDataSource.close();
        return simpleCurrentWeather;
    }
}
