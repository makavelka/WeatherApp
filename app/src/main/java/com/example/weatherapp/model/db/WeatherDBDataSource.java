package com.example.weatherapp.model.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.weatherapp.di.App;
import com.example.weatherapp.model.pojo.weather.SimpleCurrentWeather;

import java.util.ArrayList;

import javax.inject.Inject;

public class WeatherDBDataSource {

    @Inject
    WeatherDBHelper mDbHelper;

    private SQLiteDatabase mDatabase;
    private String[] mAllColumns = {WeatherDBHelper.COLUMN_ID, WeatherDBHelper.COLUMN_CITY, WeatherDBHelper.COLUMN_TYPE, WeatherDBHelper.COLUMN_TEMP};

    public WeatherDBDataSource() {
        App.getComponent().inject(this);
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public ArrayList<SimpleCurrentWeather> getAllWeathers() {
        ArrayList<SimpleCurrentWeather> weathers = new ArrayList<>();
        Cursor cursor = mDatabase.query(WeatherDBHelper.TABLE_CURRENT_WEATHER,
                mAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            weathers.add(cursorToWeather(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return weathers;
    }

    public SimpleCurrentWeather getLastWeather() {
        ArrayList<SimpleCurrentWeather> arrayList = getAllWeathers();
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList.get(arrayList.size()-1);
    }
//
//    public SimpleCurrentWeather getLastWeatherByCity(String city) {
//        ArrayList<SimpleCurrentWeather> arrayList = getWeatherByCity(city);
//        if (arrayList.size() == 0) {
//            return null;
//        }
//        return arrayList.get(arrayList.size()-1);
//    }

//    public ArrayList<SimpleCurrentWeather> getWeatherByCity(String city) {
//        String sqlQuery = "select * "
//                + "from weathers ";
////                + "where city = ?";
//        ArrayList<SimpleCurrentWeather> weathers = getAllWeathers();
//
//        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);
////        Cursor cursor = mDatabase.query(WeatherDBHelper.TABLE_CURRENT_WEATHER, mAllColumns, "city=?", new String[] { city }, null, null, null);
//        while (!cursor.isAfterLast()) {
//            weathers.add(cursorToWeather(cursor));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return weathers;
//    }

    public void addWeather(String city, String type, double temp) {
        ContentValues values = new ContentValues();
        values.put(WeatherDBHelper.COLUMN_CITY, city);
        values.put(WeatherDBHelper.COLUMN_TYPE, type);
        values.put(WeatherDBHelper.COLUMN_TEMP, temp);
        mDatabase.insert(WeatherDBHelper.TABLE_CURRENT_WEATHER, null,
                values);
        Cursor cursor = mDatabase.query(WeatherDBHelper.TABLE_CURRENT_WEATHER, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

    public void addWeather(SimpleCurrentWeather simpleCurrentWeather) {
        ContentValues values = new ContentValues();
        values.put(WeatherDBHelper.COLUMN_CITY, simpleCurrentWeather.getCity());
        values.put(WeatherDBHelper.COLUMN_TYPE, simpleCurrentWeather.getType());
        values.put(WeatherDBHelper.COLUMN_TEMP, simpleCurrentWeather.getTemp());
        mDatabase.insert(WeatherDBHelper.TABLE_CURRENT_WEATHER, null,
                values);
        Cursor cursor = mDatabase.query(WeatherDBHelper.TABLE_CURRENT_WEATHER, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

    private SimpleCurrentWeather cursorToWeather(Cursor cursor) {
        SimpleCurrentWeather data = new SimpleCurrentWeather();
        try {
            data.setCity(cursor.getString(1));
            data.setType(cursor.getString(2));
            data.setTemp(cursor.getString(3));
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return data;
    }
}