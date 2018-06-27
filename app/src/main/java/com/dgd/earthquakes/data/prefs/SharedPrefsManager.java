package com.dgd.earthquakes.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Max
 * on 02-May-17.
 */
public class SharedPrefsManager implements IPrefs {
    private static final String LAST_UPDATE = "last_update";
    private final SharedPreferences mPrefs;

    public SharedPrefsManager(Context appCtx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(appCtx);
    }

    private SharedPreferences.Editor getEditor(){
        return mPrefs.edit();
    }

    private void put(String key, long value){
        getEditor().putLong(key, value).apply();
    }

    private long get(String key, long defValue){
        return mPrefs.getLong(key, defValue);
    }

    public void saveLastUpdateDate(long millis){
        put(LAST_UPDATE, millis);
    }

    public long getLastUpdateDate(){
        return get(LAST_UPDATE, -1);
    }
}
