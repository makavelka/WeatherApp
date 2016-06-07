package com.example.weatherapp.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherDBHelper extends SQLiteOpenHelper {

    private static WeatherDBHelper sInstance;

    public static final String DB_NAME = "weather.sqlite";
    public static final int DB_VERSION = 2;
    public static final String TABLE_CURRENT_WEATHER = "weathers";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TEMP = "temp";
    public static final String COLUMN_ID = "_id";

    private final String DATABASE_CREATE = "create table " + TABLE_CURRENT_WEATHER
            + " (" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CITY + " text not null, "
            + COLUMN_TYPE + " text not null, "
            + COLUMN_TEMP + "text not null);";

    public static synchronized WeatherDBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new WeatherDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public WeatherDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS weathers");
        onCreate(db);
    }
}