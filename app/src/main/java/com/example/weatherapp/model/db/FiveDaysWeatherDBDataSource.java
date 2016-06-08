package com.example.weatherapp.model.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.weatherapp.di.App;
import com.example.weatherapp.model.pojo.weather.SimpleWeather;

import java.util.ArrayList;

import javax.inject.Inject;

public class FiveDaysWeatherDBDataSource {

    @Inject
    FiveDaysWeatherDBHelper mDbHelper;

    private SQLiteDatabase mDatabase;
    private String[] mAllColumns = {FiveDaysWeatherDBHelper.COLUMN_ID, FiveDaysWeatherDBHelper.COLUMN_DATE, FiveDaysWeatherDBHelper.COLUMN_TEMP};

    public FiveDaysWeatherDBDataSource() {
        App.getComponent().inject(this);
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public ArrayList<SimpleWeather> getAllWeathers() {
        ArrayList<SimpleWeather> weathers = new ArrayList<>();
        Cursor cursor = mDatabase.query(FiveDaysWeatherDBHelper.TABLE_CURRENT_FIVE_DAYS_WEATHER,
                mAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            weathers.add(cursorToWeather(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return weathers;
    }

    public ArrayList<SimpleWeather> getLastWeather() {
        ArrayList<SimpleWeather> arrayList = getAllWeathers();
        if (arrayList.size() == 0) {
            return arrayList;
        }
        ArrayList<SimpleWeather> simpleWeathers = new ArrayList<>();
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            simpleWeathers.add(arrayList.get(i));
            if (simpleWeathers.size() == 5) {
                break;
            }
        }
        return simpleWeathers;
    }

    public void addWeather(String date, double temp) {
        ContentValues values = new ContentValues();
        values.put(FiveDaysWeatherDBHelper.COLUMN_DATE, date);
        values.put(FiveDaysWeatherDBHelper.COLUMN_TEMP, temp);
        mDatabase.insert(FiveDaysWeatherDBHelper.TABLE_CURRENT_FIVE_DAYS_WEATHER, null,
                values);
        Cursor cursor = mDatabase.query(FiveDaysWeatherDBHelper.TABLE_CURRENT_FIVE_DAYS_WEATHER, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

    public void addWeather(SimpleWeather simpleWeather) {
        ContentValues values = new ContentValues();
        values.put(FiveDaysWeatherDBHelper.COLUMN_DATE, simpleWeather.getDay());
        values.put(FiveDaysWeatherDBHelper.COLUMN_TEMP, simpleWeather.getTemp());
        mDatabase.insert(FiveDaysWeatherDBHelper.TABLE_CURRENT_FIVE_DAYS_WEATHER, null,
                values);
        Cursor cursor = mDatabase.query(FiveDaysWeatherDBHelper.TABLE_CURRENT_FIVE_DAYS_WEATHER, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

    private SimpleWeather cursorToWeather(Cursor cursor) {
        SimpleWeather data = new SimpleWeather();
        data.setDay(cursor.getString(1));
        data.setTemp(cursor.getString(2));
        return data;
    }
}