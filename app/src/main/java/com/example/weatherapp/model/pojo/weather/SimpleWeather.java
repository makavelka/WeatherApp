package com.example.weatherapp.model.pojo.weather;

public class SimpleWeather {
    private String temp;
    private String day;

    public SimpleWeather(String day, double dayTemp, double nightTemp) {
        this.day = day;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dayTemp);
        stringBuilder.append("°/");
        stringBuilder.append(nightTemp);
        stringBuilder.append("°");
        this.temp = stringBuilder.toString();
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
