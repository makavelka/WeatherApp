package com.example.weatherapp;

public interface Const {
    String WEATHER_API_KEY = "8278cb9d20902f4ec47ce3eec5dffdd1";
    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String UI_THREAD = "UI_THREAD";
    String IO_THREAD = "IO_THREAD";
    String FLICKR_URL = "https://api.flickr.com/"
            +"services/rest/?method=flickr.photos.search&api_key=49dad1ba5c2a832dba3c1ace307eeb56" +
            "&format=json&" +
            "nojsoncallback=1" +
            "&extras=url_m";
}
