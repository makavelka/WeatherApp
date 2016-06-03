package com.example.weatherapp.presenter;

import android.os.Bundle;

import com.example.weatherapp.di.App;
import com.example.weatherapp.model.ModelImpl;
import com.example.weatherapp.model.pojo.CurrentWeather;
import com.example.weatherapp.view.CurrentWeatherView;
import com.example.weatherapp.view.IView;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class CurrentPresenterImpl implements CurrentPresenter {

    private CurrentWeatherView mView;
    private final String BUNDLE_CURRENT_KEY = "BUNDLE_CURRENT_KEY";
    private Subscription subscription = Subscriptions.empty();
    private CurrentWeather mCurrentWeather;

    public CurrentPresenterImpl() {
        App.getComponent().inject(this);
    }

    @Inject
    ModelImpl mModel;

    @Override
    public void getData(String city) {
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
                            mCurrentWeather = currentWeather;
                            mView.showWeather(currentWeather);
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
//            Weather weather = savedInstanceState.getSerializable(BUNDLE_CURRENT_KEY);
//            if (mArrayList != null) {
//                mView.showMarkers(mArrayList);
//            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onStop() {

    }
}
