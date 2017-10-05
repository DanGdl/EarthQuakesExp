package com.dgd.earthquakes.ui.quakes;

/**
 * Created by max on 04/10/17.
 */

interface IQuakesListPresenter {
    void getEarthQuakes();

    void checkNewEarthQuakes();

    void getNextBulk(long lastDate);
}
