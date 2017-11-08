package com.dgd.earthquakes.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dgd.earthquakes.BaseApplication;
import com.dgd.earthquakes.data.network.infra.Geometry;
import com.dgd.earthquakes.data.network.infra.Properties;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.models.IQuake;
import com.dgd.earthquakes.models.Quake;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Max on 30-Apr-17.
 */
@Deprecated
public class SQLiteManager implements IDataBase {
    private static SQLiteManager mInstance = new SQLiteManager(BaseApplication.getInstance());
    private DBHelper mDbHelper;

    public static SQLiteManager getInstance() {
        return mInstance;
    }

    private SQLiteManager(Context context){
        mDbHelper = new DBHelper(context);
    }

    private void close() {
        mDbHelper.close();
    }

    private SQLiteDatabase openWritable() {
        return mDbHelper.getWritableDatabase();
    }

    private SQLiteDatabase openReadable() {
        return mDbHelper.getReadableDatabase();
    }

    public void saveQuakes(List<QuakeData> quakes) {
        SQLiteDatabase db = openWritable();
        ContentValues cv = new ContentValues();
        for (QuakeData quake : quakes) {
            cv.put(DBHelper.COLUMN_QUAKE_ID, quake.getId());

            Properties prop = quake.getProperties();
            cv.put(DBHelper.COLUMN_TITLE, prop.getTitle());
            cv.put(DBHelper.COLUMN_URL, prop.getUrl());
            cv.put(DBHelper.COLUMN_MAGNITUDE, String.valueOf(prop.getMagnitude()));
            cv.put(DBHelper.COLUMN_TIME, prop.getTime());

            Geometry geom = quake.getGeometry();
            cv.put(DBHelper.COLUMN_LNG, geom.getCoordinates().get(0));
            cv.put(DBHelper.COLUMN_LAT, geom.getCoordinates().get(1));

            int result = db.update(DBHelper.TABLE_QUAKES, cv, DBHelper.COLUMN_QUAKE_ID + " = '" + quake.getId() + "'", null);
            if(result == 0) {
                db.insert(DBHelper.TABLE_QUAKES, null, cv);
            }
        }
        close();
    }

    public List<IQuake> getQuakesBulk(long date) {
        List<IQuake> quakes = new ArrayList<>();
        Cursor c;
        if(date == 0) {
            c = openReadable().query(DBHelper.TABLE_QUAKES, null, null, null, null, null, DBHelper.COLUMN_TIME);
        }
        else{
            c = openReadable().query(DBHelper.TABLE_QUAKES, null,
                    DBHelper.COLUMN_TIME + " < ?", new String[]{String.valueOf(date)},
                    null, null, DBHelper.COLUMN_TIME);
        }

        if(c != null && c.moveToLast()) {
            int counter = 0;
            Log.d("SQL", "size " + c.getCount());
            do {
                quakes.add(createQuakeFromCursor(c));
                counter++;
            } while (counter < 20 && c.moveToPrevious());
            c.close();
        }
        close();
        return quakes;
    }

    private Quake createQuakeFromCursor(Cursor c) {
        Quake quake = new Quake();
        quake.setId(c.getString(1));
        quake.setTitle(c.getString(2));
        quake.setLink(c.getString(3));
        quake.setMagnitude(c.getString(4));
        quake.setDate(new Date(c.getLong(5)));

        quake.setLongitude(c.getDouble(6));
        quake.setLatitude(c.getDouble(7));

        return quake;
    }
}
