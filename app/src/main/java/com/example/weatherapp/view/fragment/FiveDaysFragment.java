package com.example.weatherapp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.model.pojo.weather.FiveDaysWeather;
import com.example.weatherapp.view.FiveDaysWeatherView;

import java.util.List;

public class FiveDaysFragment extends Fragment implements FiveDaysWeatherView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_five_days, container, false);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWeather(List<FiveDaysWeather> list) {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showError(String error) {
        showToast(error);
    }
}
