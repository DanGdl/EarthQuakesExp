package com.dgd.earthquakes.data;

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.models.Quake;

import java.util.Date;

import io.realm.RealmResults;

/**
 * Created by Max on 23-Jun-17.
 */

public interface IRepo {

    void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener);

    void checkNewEarthquakes();

    void getEarthquakes();

    RealmResults<Quake> getAllQuakes();

    void closeRealm();
}
