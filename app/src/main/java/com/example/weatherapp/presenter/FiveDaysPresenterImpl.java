package com.example.weatherapp.presenter;

import android.os.Bundle;

import com.example.weatherapp.di.App;
import com.example.weatherapp.model.ModelImpl;
import com.example.weatherapp.model.pojo.weather.CurrentWeather;
import com.example.weatherapp.model.pojo.weather.FiveDaysWeather;
import com.example.weatherapp.model.pojo.weather.SimpleWeather;
import com.example.weatherapp.view.FiveDaysWeatherView;
import com.example.weatherapp.view.IView;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class FiveDaysPresenterImpl implements FiveDaysPresenter {

    private FiveDaysWeatherView mView;
    private final String BUNDLE_CURRENT_KEY = "BUNDLE_CURRENT_KEY";
    private Subscription subscription = Subscriptions.empty();
    private CurrentWeather mCurrentWeather;

    public FiveDaysPresenterImpl() {
        App.getComponent().inject(this);
    }

    @Inject
    ModelImpl mModel;

    @Override
    public void getData(String city) {
        ArrayList<SimpleWeather> simpleWeather = mModel.getLastFiveDaysWeather();
        if (simpleWeather.size() > 0) {
            mView.showWeather(simpleWeather);
        }
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = mModel.getFiveDaysWeather(city).subscribe(new Observer<FiveDaysWeather>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(FiveDaysWeather fiveDaysWeather) {
                parseData(fiveDaysWeather);
            }
        });
    }

    @Override
    public void parseData(FiveDaysWeather fiveDaysWeather) {
        ArrayList<SimpleWeather> simpleWeathers = new ArrayList<>();
        int z = 0;
        int x = fiveDaysWeather.getList().size() % 8;
        if (x != 0) {
            while (z < fiveDaysWeather.getList().size()) {
                if (z == 0) {
                    simpleWeathers.add(new SimpleWeather(fiveDaysWeather.getList().get(z).getDtTxt().substring(0, 10),
                            fiveDaysWeather.getList().get(z).getMain().getTemp().intValue(),
                            fiveDaysWeather.getList().get(x).getMain().getTemp().intValue()));
                    if (x > 0) {
                        z += x;
                    } else {
                        z += 8;
                    }
                    continue;
                }
                simpleWeathers.add(new SimpleWeather(fiveDaysWeather.getList().get(z).getDtTxt().substring(0, 10),
                        fiveDaysWeather.getList().get(z).getMain().getTemp().intValue(),
                        fiveDaysWeather.getList().get(z + 4).getMain().getTemp().intValue()));
                z += 8;
            }
        } else {
            for (int i = 0; i < fiveDaysWeather.getList().size(); i += 8) {
                simpleWeathers.add(new SimpleWeather(fiveDaysWeather.getList().get(i).getDtTxt().substring(0, 10),
                        fiveDaysWeather.getList().get(i).getMain().getTemp().intValue(),
                        fiveDaysWeather.getList().get(i + 4).getMain().getTemp().intValue()));
            }
        }
        mModel.saveToDb(simpleWeathers);
        mView.showWeather(simpleWeathers);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, IView view) {
        mView = (FiveDaysWeatherView) view;
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
