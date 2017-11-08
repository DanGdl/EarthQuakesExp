package com.dgd.earthquakes.ui.quakes;

import com.dgd.earthquakes.common.IView;
import com.dgd.earthquakes.models.Quake;

import io.realm.RealmResults;

/**
 * Created by max on 04/10/17.
 */

interface IQuakesListView extends IView {
    void updateEarthQuakes(RealmResults<Quake> quakes);
}
