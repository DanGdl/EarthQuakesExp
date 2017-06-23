package com.dgd.earthquakes.screens.fragments;

import com.dgd.earthquakes.models.Quake;

import io.realm.RealmResults;

/**
 * Created by Max on 22-Jun-17.
 */

public interface IEarthQuakesFragment {
    void updateEarthQuakes(RealmResults<Quake> quakes);

    void hideProgress();
}
