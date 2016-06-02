package com.example.weatherapp.model.api;

import android.content.Context;

import com.example.weatherapp.di.App;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Класс, используемый для настройки Picasso - библиотеки, работающей с загрузкой изображений
 */
public class PicassoService {

    //Инъекция контекста, необходим для инициализации кэша
    @Inject
    Context mContext;
    @Inject
    OkHttpClient mHttpClient;
    //Вызов метода inject в конструкторе класса, чтобы инъектируемые данные были доступны сразу
    public PicassoService() {
        App.getComponent().inject(this);
    }

    /**
     * Настройка Picasso
     * @return экземпляр Picasso
     */
    public Picasso getPicasso() {

        //Установка OkHttpClient стандартным загрузчиком изображений из интернета
        OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(mHttpClient);
        return new Picasso.Builder(mContext).downloader(okHttpDownloader).build();
    }
}
