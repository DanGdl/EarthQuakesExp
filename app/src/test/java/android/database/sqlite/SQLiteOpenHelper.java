package android.database.sqlite;

import android.content.Context;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public abstract class SQLiteOpenHelper {

    public SQLiteOpenHelper(Context context, String dbName, SQLiteDatabase.CursorFactory cursorFactory, int dbVersion){}

    public abstract void onCreate(SQLiteDatabase db);

    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

}
