package com.example.weatherapp.model.pojo.weather;

/**
 * Created by Ghost Surfer on 08.06.2016.
 */
public class SimpleCurrentWeather {
    private String city;
    private String type;
    private String temp;

    public SimpleCurrentWeather(String city, String type, String temp) {
        this.city = city;
        this.type = type;
        this.temp = temp;
    }

    public SimpleCurrentWeather() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
