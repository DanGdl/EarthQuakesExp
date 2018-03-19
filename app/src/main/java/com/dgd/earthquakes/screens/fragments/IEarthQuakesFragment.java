package com.dgd.earthquakes.screens.fragments;

import com.dgd.earthquakes.models.IQuake;

import java.util.List;

/**
 * Created by Max
 * on 22-Jun-17.
 */

public interface IEarthQuakesFragment {
    void updateEarthQuakes(List<IQuake> quakes);
}
