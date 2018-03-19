package com.dgd.earthquakes.ui.quakes;

import com.dgd.earthquakes.common.IView;
import com.dgd.earthquakes.models.Quake;

import io.realm.RealmResults;

/**
 * Created by max
 * on 2/2/18.
 */

public class QuackesScreenContract {
    interface IQuakesListPresenter {
        void getEarthQuakes(SearchDTO searchParams);

        void checkNewEarthQuakes();

        void getNextBulk(long lastDate);
    }

    interface IQuakesListView extends IView {
        void updateEarthQuakes(RealmResults<Quake> quakes);
    }
}
