package com.example.weatherapp.model.api;

import android.content.Context;

import com.example.weatherapp.di.App;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class HttpClient {

    @Inject
    Context mContext;

    public HttpClient() {
        App.getComponent().inject(this);
    }

    public OkHttpClient getHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.cache(new Cache(mContext.getFilesDir(), Integer.MAX_VALUE));
        okHttpClient.addInterceptor(new ResponseCacheInterceptor());
        return okHttpClient.build();
    }
}
