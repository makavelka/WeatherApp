package com.example.weatherapp.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.weatherapp.GeocoderUtils;
import com.example.weatherapp.Utils;
import com.example.weatherapp.di.App;
import com.example.weatherapp.model.ModelImpl;
import com.example.weatherapp.model.pojo.event.LatLngEvent;
import com.example.weatherapp.model.pojo.weather.CurrentWeather;
import com.example.weatherapp.model.pojo.weather.SimpleCurrentWeather;
import com.example.weatherapp.view.CurrentWeatherView;
import com.example.weatherapp.view.IView;

import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class CurrentPresenterImpl implements CurrentPresenter {

    @Inject
    GeocoderUtils mGeocoderUtils;
    @Inject
    Utils mUtils;
    @Inject
    Context mContext;

    private CurrentWeatherView mView;
    private final String BUNDLE_CURRENT_KEY = "BUNDLE_CURRENT_KEY";
    private Subscription subscription = Subscriptions.empty();
    private SimpleCurrentWeather mSimpleCurrentWeather;


    public CurrentPresenterImpl() {
        App.getComponent().inject(this);
    }

    @Inject
    ModelImpl mModel;

    @Override
    public void getData(String city) {
        SimpleCurrentWeather simpleCurrentWeather = mModel.getLastWeather();
        if (simpleCurrentWeather != null) {
            mView.showWeather(simpleCurrentWeather);
        }
        if (city == null) {
            return;
        }
        Subscription subscription = Subscriptions.empty();
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = mModel.getCurrentWeather(city)
                .subscribe(new Observer<CurrentWeather>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(CurrentWeather currentWeather) {
                        if (currentWeather != null) {
                            mView.showWeather(currentWeather);
                            SimpleCurrentWeather simpleCurrentWeather = new SimpleCurrentWeather(currentWeather.getName(),
                                    currentWeather.getWeather().get(0).getMain(),
                                    String.valueOf(currentWeather.getMain().getTemp().intValue()));
                            mSimpleCurrentWeather = simpleCurrentWeather;
                            mModel.saveToDb(simpleCurrentWeather);
                        } else {
                            mView.showNoData();
                        }
                    }
                });
    }

    @Override
    public void onCreate(Bundle savedInstanceState, IView view) {
        mView = (CurrentWeatherView) view;
        if (savedInstanceState != null) {
            mSimpleCurrentWeather = savedInstanceState.getParcelable(BUNDLE_CURRENT_KEY);
            if (mSimpleCurrentWeather != null) {
                mView.showWeather(mSimpleCurrentWeather);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mSimpleCurrentWeather != null)
            outState.putParcelable(BUNDLE_CURRENT_KEY, mSimpleCurrentWeather);
    }

    @Override
    public void onStop() {

    }


    @Override
    public String getCity(double lat, double lon) {
        String name = null;
        try {
            name = mGeocoderUtils.getNameByLatLng(lat, lon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    @Subscribe
    public void onEvent(LatLngEvent event) {
        getData(getCity(event.getLat(), event.getLon()));
    }
}
