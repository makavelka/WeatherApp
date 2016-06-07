package com.example.weatherapp.presenter;

import com.google.android.gms.maps.model.LatLng;

public interface MainPresenter extends Presenter {
    void findCity(String cityName);
    void getImagesList(double lat, double lon);
    LatLng getLastKnownLocation();
}
