package com.example.weatherapp.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.weatherapp.Utils;
import com.example.weatherapp.di.App;
import com.example.weatherapp.model.ModelImpl;
import com.example.weatherapp.model.pojo.flickr.Flickr;
import com.example.weatherapp.view.IView;
import com.example.weatherapp.view.MainView;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class MainPresenterImpl implements MainPresenter {

    @Inject
    ModelImpl mModel;
    @Inject
    Context mContext;
    @Inject
    Utils mUtils;

    private MainView mView;

    public MainPresenterImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public void findCity(String cityName) {

    }

    @Override
    public void getImagesList(double lat, double lon) {
//        if (mUtils.isInternetConnected()) {
//            mView.showToast(mContext.getString(R.string.no_internet));
//            return;
//        }
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
    public LatLng getLastKnownLocation() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location lastlocation = locationManager.getLastKnownLocation(locationProvider);
        if (lastlocation != null) {
            return new LatLng(lastlocation.getLatitude(), lastlocation.getLongitude());
        } else {
            return null;
        }

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
