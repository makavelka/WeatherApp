package com.example.weatherapp.di.module;

import com.example.weatherapp.model.api.GoogleApi;
import com.example.weatherapp.model.api.HttpClient;
import com.example.weatherapp.model.api.PicassoService;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
@Module
public class ApiModule {
    @Provides
    @Singleton
    Picasso providePicasso() {
        return new PicassoService().getPicasso();
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient() {
        return new HttpClient().getHttpClient();
    }

    @Provides
    @Singleton
    GoogleApiClient provideGoogleApiClient() {
        return new GoogleApi().getGoogleApiClient();
    }
}
