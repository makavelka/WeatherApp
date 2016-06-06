package com.example.weatherapp.presenter;

import android.os.Bundle;

import com.example.weatherapp.di.App;
import com.example.weatherapp.model.ModelImpl;
import com.example.weatherapp.model.pojo.flickr.Flickr;
import com.example.weatherapp.view.IView;
import com.example.weatherapp.view.MainView;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class MainPresenterImpl implements MainPresenter {

    @Inject
    ModelImpl mModel;

    private MainView mView;

    public MainPresenterImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public void findCity(String cityName) {

    }

    @Override
    public void getImagesList(double lat, double lon) {
        Subscription subscription = Subscriptions.empty();
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = mModel.getImages(lat, lon)
                .subscribe(new Observer<Flickr>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Flickr flickr) {
                        mView.showBackground(flickr.getPhotos().getPhoto().get(0).getUrlO());
                    }
                });
    }

    @Override
    public void onCreate(Bundle savedInstanceState, IView view) {
        mView = (MainView) view;
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
