package com.dgd.earthquakes.data.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.dgd.earthquakes.BaseApplication
import com.dgd.earthquakes.data.network.infra.QuakeData
import com.dgd.earthquakes.models.IQuake
import com.dgd.earthquakes.models.Quake
import java.util.*

/**
 * Created by Max
 * on 30-Apr-17.
 */

class SQLiteManager private constructor(context: Context) : IDataBase {
    private val mDbHelper: DBHelper = DBHelper(context)

    private fun close() {
        mDbHelper.close()
    }

    private fun openWritable(): SQLiteDatabase {
        return mDbHelper.writableDatabase
    }

    private fun openReadable(): SQLiteDatabase {
        return mDbHelper.readableDatabase
    }

    @Suppress("ProtectedInFinal", "Unused")
    protected fun finalize() {
        close()
    }

    override fun saveQuakes(quakes: List<QuakeData>) {
        val db = openWritable()
        val cv = ContentValues()
        for (quake in quakes) {
            cv.put(DBHelper.COLUMN_QUAKE_ID, quake.id)

            val prop = quake.properties
            cv.put(DBHelper.COLUMN_TITLE, prop!!.title)
            cv.put(DBHelper.COLUMN_URL, prop.url)
            cv.put(DBHelper.COLUMN_MAGNITUDE, prop.magnitude.toString())
            cv.put(DBHelper.COLUMN_TIME, prop.time)

            val geom = quake.geometry
            cv.put(DBHelper.COLUMN_LNG, geom?.coordinates!![0])
            cv.put(DBHelper.COLUMN_LAT, geom.coordinates!![1])

            val result = db.update(DBHelper.TABLE_QUAKES, cv, DBHelper.COLUMN_QUAKE_ID + " = '" + quake.id + "'", null)
            if (result == 0) {
                db.insert(DBHelper.TABLE_QUAKES, null, cv)
            }
        }
    }

    override fun getQuakesBulk(date: Long): List<IQuake> {
        val quakes = ArrayList<IQuake>()
        val c: Cursor?
        if (date == 0L) {
            c = openReadable().query(DBHelper.TABLE_QUAKES, null, null, null, null, null, DBHelper.COLUMN_TIME)
        } else {
            c = openReadable().query(DBHelper.TABLE_QUAKES, null,
                    DBHelper.COLUMN_TIME + " < ?", arrayOf(date.toString()), null, null, DBHelper.COLUMN_TIME)
        }

        if (c != null && c.moveToLast()) {
            var counter = 0
            Log.d("SQL", "size " + c.count)
            do {
                quakes.add(createQuakeFromCursor(c))
                counter++
            } while (counter < 20 && c.moveToPrevious())
            c.close()
        }
        return quakes
    }

    private fun createQuakeFromCursor(c: Cursor): Quake {
        val quake = Quake()
        quake.id = c.getString(1)
        quake.title = c.getString(2)
        quake.link = c.getString(3)
        quake.magnitude = c.getString(4)
        quake.date = Date(c.getLong(5))

        quake.longitude = c.getDouble(6)
        quake.latitude = c.getDouble(7)

        return quake
    }

    companion object {
        val instance = SQLiteManager(BaseApplication.instance!!)
    }
}
