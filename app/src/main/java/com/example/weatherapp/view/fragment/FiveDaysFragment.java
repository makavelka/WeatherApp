package com.example.weatherapp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.di.App;
import com.example.weatherapp.model.pojo.weather.SimpleWeather;
import com.example.weatherapp.presenter.FiveDaysPresenterImpl;
import com.example.weatherapp.view.FiveDaysWeatherView;
import com.example.weatherapp.view.SimpleAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FiveDaysFragment extends Fragment implements FiveDaysWeatherView {

    @Inject
    Picasso mPicasso;
    @Inject
    FiveDaysPresenterImpl mPresenter;

    @Bind(R.id.noData_textView_fiveDaysFragment)
    TextView mNoData;
    @Bind(R.id.background_imageView_fiveDaysFragment)
    ImageView mBackground;
    @Bind(R.id.temp_recyclerView_fiveDaysFragment)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        mPresenter.onCreate(savedInstanceState, this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_five_days, container, false);
        mPresenter.getData("Vladikavkaz");
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWeather(List<SimpleWeather> list) {
        SimpleAdapter adapter = new SimpleAdapter((ArrayList) list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void showNoData() {
        mNoData.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        showToast(error);
    }

}
