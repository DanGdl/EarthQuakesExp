package com.dgd.earthquakes.ui.quakes;

import com.dgd.earthquakes.common.IView;
import com.dgd.earthquakes.models.Quake;

import java.util.List;

/**
 * Created by max
 * on 2/2/18.
 */

class QuackesScreenContract {
    interface IQuakesListPresenter {
        void getEarthQuakes(SearchDTO searchParams);

        void checkNewEarthQuakes();

        void getNextBulk(long lastDate);
    }

    interface IQuakesListView extends IView {
        void updateEarthQuakes(List<Quake> quakes);
    }
}
