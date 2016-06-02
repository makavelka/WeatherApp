package com.example.weatherapp.model;

public interface Model {
    void getDataFromDb();
    void getDataFromInternet();
    void getData();
    boolean checkIfDataExistInDb();
}
