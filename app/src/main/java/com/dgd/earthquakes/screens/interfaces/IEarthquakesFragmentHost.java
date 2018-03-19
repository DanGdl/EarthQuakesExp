package com.dgd.earthquakes.screens.interfaces;

/**
 * Created by Max
 * on 01-May-17.
 */

public interface IEarthquakesFragmentHost {
    void getEarthQuakes();

    void refreshEarthQuakes(final IQuakesUpdated listener);

    void getNextBulk(long lastDate, IQuakesUpdated listener);
}
