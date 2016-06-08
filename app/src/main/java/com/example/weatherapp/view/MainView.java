package com.example.weatherapp.view;

public interface MainView extends IView {
    void loadFragmentsToViewPager();
    void requestPermission();
    void showBackground(String url);
    void setCityManual();
}
