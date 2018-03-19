package com.dgd.earthquakes.util;

/**
 * Created by Max
 * on 23-Jun-17.
 */

public  interface IPrefs {

    void putMinMagnitude();

    void saveLastUpdateDate(long millis);

    long getLastUpdateDate();
}
