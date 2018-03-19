package com.dgd.earthquakes.screens.interfaces;

import com.dgd.earthquakes.models.IQuake;

import java.util.List;

/**
 * Created by Max
 * on 02-May-17.
 */
public interface IQuakesUpdated {
    void quakesUpdated(List<IQuake> quakes);
}
