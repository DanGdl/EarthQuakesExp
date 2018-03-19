package com.dgd.earthquakes.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Max
 * on 30-Apr-17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quakes.db";
    private static final int    DATABASE_VERSION = 1;

    public static final String TABLE_QUAKES = "earthquakes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUAKE_ID = "quake_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_MAGNITUDE = "mag";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_LNG = "lng";
    public static final String COLUMN_LAT = "lat";


    // Database creation sql statement
    private static final String EARTHQUAKES_TABLE_CREATE = "create table " +
            TABLE_QUAKES     + "( " +
            COLUMN_ID        + " integer primary key autoincrement, " +
            COLUMN_QUAKE_ID  + " text not null, " +
            COLUMN_TITLE     + " text, " +
            COLUMN_URL       + " text, " +
            COLUMN_MAGNITUDE + " text, " +
            COLUMN_TIME      + " integer, " +
            COLUMN_LNG       + " real, " +
            COLUMN_LAT       + " real " +
            ");";

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(EARTHQUAKES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUAKES);
        onCreate(db);
    }
}

