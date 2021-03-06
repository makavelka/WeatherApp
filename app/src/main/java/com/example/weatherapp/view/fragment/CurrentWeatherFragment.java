package com.example.weatherapp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.di.App;
import com.example.weatherapp.model.pojo.event.CityEvent;
import com.example.weatherapp.model.pojo.weather.CurrentWeather;
import com.example.weatherapp.model.pojo.weather.SimpleCurrentWeather;
import com.example.weatherapp.presenter.CurrentPresenterImpl;
import com.example.weatherapp.view.CurrentWeatherView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CurrentWeatherFragment extends Fragment implements CurrentWeatherView {

    @Inject
    CurrentPresenterImpl mPresenter;
    @Inject
    Picasso mPicasso;
    @Inject
    EventBus mEventBus;

    @Bind(R.id.city_textView_currentFragment)
    TextView mCity;
    @Bind(R.id.temp_textView_currentFragment)
    TextView mTemp;
    @Bind(R.id.type_textView_currentFragment)
    TextView mType;
    @Bind(R.id.data_layout_currentFragment)
    View mDataView;
    @Bind(R.id.noData_textView_currentFragment)
    TextView mNoData;
    @Bind(R.id.background_imageView_currentFragment)
    ImageView mBackground;

    private String mCityString;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        mPresenter.onCreate(savedInstanceState, this);
        mEventBus.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current_weather, container, false);
        ButterKnife.bind(this, v);
        mPresenter.getData(mCityString);
        return v;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWeather(CurrentWeather currentWeather) {
        mNoData.setVisibility(View.GONE);
        mCity.setText(currentWeather.getName());
        mTemp.setText(String.valueOf(currentWeather.getMain().getTemp().intValue()) + "°");
        mType.setText(currentWeather.getWeather().get(0).getMain());
    }

    @Override
    public void showWeather(SimpleCurrentWeather weather) {
        mNoData.setVisibility(View.GONE);
        mCity.setText(weather.getCity());
        mTemp.setText(weather.getTemp() + "°");
        mType.setText(weather.getType());
    }

    @Override
    public void showNoData() {
        mNoData.setVisibility(View.VISIBLE);
        mDataView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        showToast(error);
    }

    @Subscribe
    public void onEvent(CityEvent event) {
        mCityString = event.getCity();
        mPresenter.getData(event.getCity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
    }
}