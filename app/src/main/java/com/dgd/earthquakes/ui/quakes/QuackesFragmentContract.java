package com.dgd.earthquakes.ui.quakes;

import com.dgd.earthquakes.models.Quake;

import java.util.List;

/**
 * Created by max
 * on 2/2/18.
 */

public class QuackesFragmentContract {

    public interface IEarthQuakesFragment {
        void updateEarthQuakes(List<Quake> quakes);
    }

    interface IEarthQuakesFragmentHost {
        void getNextBulk(long lastDate);

        void getEarthQuakes(SearchDTO searchData);

        void refreshEarthQuakes();
    }
}
