package com.example.weatherapp.di.module;

import android.content.Context;
import android.location.Geocoder;

import com.example.weatherapp.GeocoderUtils;
import com.example.weatherapp.Utils;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    Utils provideUtils() {
        return new Utils();
    }

    @Provides
    @Singleton
    Geocoder provideGeocoder(Context context) {
        return new Geocoder(context, Locale.getDefault());
    }

    @Provides
    @Singleton
    GeocoderUtils provideGeocodeUtils() {
        return new GeocoderUtils();
    }
}