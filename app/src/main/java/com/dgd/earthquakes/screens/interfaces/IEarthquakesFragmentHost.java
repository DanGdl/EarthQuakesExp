package com.dgd.earthquakes.screens.interfaces;

import com.dgd.earthquakes.models.IQuake;
import com.dgd.earthquakes.models.Quake;
import com.dgd.earthquakes.screens.fragments.EarthQuakesFragment;

import java.util.List;

/**
 * Created by Max on 01-May-17.
 */

public interface IEarthquakesFragmentHost {
    void getEarthQuakes();

    void refreshEarthQuakes(final IQuakesUpdated listener);

    void getNextBulk(long lastDate, IQuakesUpdated listener);
}
