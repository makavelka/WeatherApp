package com.example.weatherapp;

import android.location.Address;
import android.location.Geocoder;

import com.example.weatherapp.di.App;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

public class GeocoderUtils {

    @Inject
    Geocoder mGeocoder;

    public GeocoderUtils() {
        App.getComponent().inject(this);
    }

    public LatLng getLatLngByName(String name) throws IOException {
        ArrayList<Address> addresses = (ArrayList<Address>) mGeocoder.getFromLocationName(name, 1);
        if (addresses.size() > 0) {
            Address address = addresses.get(0);
            return new LatLng(address.getLatitude(), address.getLongitude());
        }
        return null;
    }

    public String getNameByLatLng(double lat, double lon) throws IOException {
        ArrayList<Address> addresses = (ArrayList<Address>) mGeocoder.getFromLocation(lat, lon, 1);
        if (addresses.size() > 0) {
            return addresses.get(0).getLocality();
        }
        return null;
    }
}
