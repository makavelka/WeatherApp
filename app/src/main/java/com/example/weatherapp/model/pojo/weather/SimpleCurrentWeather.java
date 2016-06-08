package com.example.weatherapp.model.pojo.weather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ghost Surfer on 08.06.2016.
 */
public class SimpleCurrentWeather implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.city);
        dest.writeString(this.type);
        dest.writeString(this.temp);
    }

    protected SimpleCurrentWeather(Parcel in) {
        this.city = in.readString();
        this.type = in.readString();
        this.temp = in.readString();
    }

    public static final Parcelable.Creator<SimpleCurrentWeather> CREATOR = new Parcelable.Creator<SimpleCurrentWeather>() {
        @Override
        public SimpleCurrentWeather createFromParcel(Parcel source) {
            return new SimpleCurrentWeather(source);
        }

        @Override
        public SimpleCurrentWeather[] newArray(int size) {
            return new SimpleCurrentWeather[size];
        }
    };
}
