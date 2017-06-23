package com.dgd.earthquakes.data;

import com.dgd.earthquakes.models.IQuake;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;

import java.util.List;

/**
 * Created by Max on 22-Jun-17.
 */

public interface IRepo {
    void subscribeToEarthquakes(Updatable updatable);

    void unsubscribeFromEarthquakes(Updatable updatable);

    Repository<Result<List<IQuake>>> getEarthQuakesRepository();

    void checkNewEarthquakes();
}
