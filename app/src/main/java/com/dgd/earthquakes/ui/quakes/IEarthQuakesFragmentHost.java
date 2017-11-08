package com.dgd.earthquakes.ui.quakes;

/**
 * Created by max on 29/09/17.
 */

interface IEarthQuakesFragmentHost {
    void getNextBulk(long lastDate);

    void getEarthQuakes(String query);

    void refreshEarthQuakes();
}
