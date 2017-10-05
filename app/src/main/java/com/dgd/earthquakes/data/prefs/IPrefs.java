package com.dgd.earthquakes.data.prefs;

/**
 * Created by Max on 23-Jun-17.
 */

public  interface IPrefs {

    void saveLastUpdateDate(long millis);

    long getLastUpdateDate();
}
