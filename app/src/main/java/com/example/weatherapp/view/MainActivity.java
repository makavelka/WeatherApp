package com.example.weatherapp.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weatherapp.GeocoderUtils;
import com.example.weatherapp.R;
import com.example.weatherapp.di.App;
import com.example.weatherapp.model.pojo.event.CityEvent;
import com.example.weatherapp.model.pojo.event.LatLngEvent;
import com.example.weatherapp.presenter.MainPresenterImpl;
import com.example.weatherapp.view.fragment.CurrentWeatherFragment;
import com.example.weatherapp.view.fragment.FiveDaysFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    @Bind(R.id.viewPager_mainActivity)
    ViewPager mViewPager;
    @Bind(R.id.city_editText_mainActivity)
    EditText mCity;
    @Bind(R.id.tabs_mainActivity)
    TabLayout mTabLayout;
    @Bind(R.id.background_imageView_mainActivity)
    ImageView mBackground;

    @Inject
    Picasso mPicasso;
    @Inject
    MainPresenterImpl mPresenter;
    @Inject
    GeocoderUtils mGeocoder;
    @Inject
    EventBus mEventBus;

    private GoogleApiClient mGoogleApiClient;


    private final int REQUEST_ACCESS_FINE_LOCATION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);
        mPresenter.onCreate(savedInstanceState, this);
        ButterKnife.bind(this);
        loadFragmentsToViewPager();
        requestPermission();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .build();
        }
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
            return;
        }
        LatLng latLng = mPresenter.getLastKnownLocation();
        if (latLng == null) {
            return;
        } else {
            mPresenter.getImagesList(latLng.latitude, latLng.longitude);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LatLng latLng = mPresenter.getLastKnownLocation();
                    if (latLng != null) {
                        mPresenter.getImagesList(latLng.latitude, latLng.longitude);
                    }
                } else {
                    showToast(getString(R.string.permission_denied_location));
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void showBackground(String url) {
        mPicasso.with(this)
                .load(url)
                .fit()
                .centerInside()
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(mBackground, new Callback() {
                    @Override
                    public void onSuccess() {
                        mPicasso.with(MainActivity.this)
                                .load(url)
                                .fit()
                                .centerInside()
                                .into(mBackground);
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    @OnClick(R.id.setCity_button_mainActivity)
    @Override
    public void setCityManual() {
        try {
            LatLng latLng = mGeocoder.getLatLngByName(mCity.getText().toString());
            if (latLng != null) {
                mPresenter.getImagesList(latLng.latitude, latLng.longitude);
                mEventBus.post(new CityEvent(mCity.getText().toString()));
            } else {
                mCity.setError(getString(R.string.city_not_found));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mEventBus.post(new LatLngEvent(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
        } else {
            showToast(getString(R.string.need_city));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        connectionResult.getErrorMessage();
    }
}
