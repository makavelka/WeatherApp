package com.example.weatherapp.model.pojo.event;

/**
 * Created by Android on 08.06.2016.
 */
public class LatLngEvent {
    private double lat;
    private double lon;

    public LatLngEvent(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
