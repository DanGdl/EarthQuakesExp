package com.dgd.earthquakes.data;

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.models.Quake;

import java.util.Date;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Max on 23-Jun-17.
 */

public interface IRepo {

    void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener);

    void checkNewEarthquakes(IQuakesCallbackListener listener);

    RealmResults<Quake> getAllQuakes(String query);

    void closeRealm();

    void saveLastUpdate(long time);

    void saveToRealm(List<QuakeData> quakes);
}
