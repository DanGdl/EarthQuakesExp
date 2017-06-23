package com.dgd.earthquakes.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dgd.earthquakes.BaseApplication;

/**
 * Created by Max on 02-May-17.
 */
public class SharedPrefsManager {
    private static final String LAST_UPDATE = "last_update";
    private static SharedPrefsManager ourInstance = new SharedPrefsManager();
    private SharedPreferences mPrefs;

    public static SharedPrefsManager getInstance() {
        return ourInstance;
    }

    private SharedPrefsManager() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance());
    }

    public void putMinMagnitude(){

    }

    public void saveLastUpdateDate(long millis){
        mPrefs.edit().putLong(LAST_UPDATE, millis).apply();
    }

    public long getLastUpdateDate(){
        return mPrefs.getLong(LAST_UPDATE, -1);
    }
}
