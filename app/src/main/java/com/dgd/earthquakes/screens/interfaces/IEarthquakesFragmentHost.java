package com.dgd.earthquakes.screens.interfaces;

/**
 * Created by Max on 01-May-17.
 */

public interface IEarthquakesFragmentHost {
    void getEarthQuakes();

    void refreshEarthQuakes();

    void getNextBulk(long lastDate);
}
