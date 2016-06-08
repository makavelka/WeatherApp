package com.example.weatherapp.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FiveDaysWeatherDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "fivedays.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TABLE_CURRENT_FIVE_DAYS_WEATHER = "fivedays";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TEMP = "temp";
    public static final String COLUMN_ID = "_id";

    private final String DATABASE_CREATE = "create table " + TABLE_CURRENT_FIVE_DAYS_WEATHER
            + " (" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DATE + " text not null, "
            + COLUMN_TEMP + " text not null);";

    public FiveDaysWeatherDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS five_days_weathers");
        onCreate(db);
    }
}