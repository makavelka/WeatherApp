package com.example.weatherapp;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;

import com.example.weatherapp.di.App;

import javax.inject.Inject;

public class Utils {

    @Inject
    Context mContext;

    /**
     * В конструкторе делаем инъекцию необходимых нам данных
     */
    public Utils() {
        App.getComponent().inject(this);
    }

    /**
     * Проверить наличие подключения к интернету.
     */
    public boolean isInternetConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    /**
     * Возвращает информацию о том, является ли устройство планшетом
     *
     * @param context - контекст
     * @return true - если планшет, иначе false
     */
    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
}
