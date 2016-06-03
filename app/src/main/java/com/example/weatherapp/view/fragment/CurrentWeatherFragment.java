package com.example.weatherapp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.di.App;
import com.example.weatherapp.model.pojo.CurrentWeather;
import com.example.weatherapp.presenter.CurrentPresenterImpl;
import com.example.weatherapp.view.CurrentWeatherView;

import javax.inject.Inject;

public class CurrentWeatherFragment extends Fragment implements CurrentWeatherView {

    @Inject
    CurrentPresenterImpl mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        mPresenter.onCreate(savedInstanceState, this);
        mPresenter.getData("Владикавказ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_weather, container, false);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWeather(CurrentWeather currentWeather) {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showError(String error) {
        showToast(error);
    }
}
