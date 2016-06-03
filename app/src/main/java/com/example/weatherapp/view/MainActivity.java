package com.example.weatherapp.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.view.fragment.CurrentWeatherFragment;
import com.example.weatherapp.view.fragment.FiveDaysFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.viewPager_mainActivity)
    ViewPager mViewPager;
    @Bind(R.id.city_editText_mainActivity)
    EditText mCity;
    @Bind(R.id.tabs_mainActivity)
    TabLayout mTabLayout;

    private int REQUEST_ACCESS_FINE_LOCATION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadFragmentsToViewPager();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadFragmentsToViewPager() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CurrentWeatherFragment(), getString(R.string.first_tab));
        adapter.addFragment(new FiveDaysFragment(), getString(R.string.second_tab));
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void requestPermission() {
        boolean isLocationsEnabled = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if (!isLocationsEnabled) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_ACCESS_FINE_LOCATION);
        }
    }
}
