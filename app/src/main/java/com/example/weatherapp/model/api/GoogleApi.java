package com.example.weatherapp.model.api;

import android.content.Context;

import com.example.weatherapp.di.App;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import javax.inject.Inject;

public class GoogleApi {

    @Inject
    Context mContext;

    public GoogleApi() {
        App.getComponent().inject(this);
    }

    public GoogleApiClient getGoogleApiClient() {
        return new GoogleApiClient
                .Builder(mContext)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
    }
}
