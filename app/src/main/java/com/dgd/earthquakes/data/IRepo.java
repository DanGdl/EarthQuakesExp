package com.dgd.earthquakes.data;

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.models.Quake;
import com.dgd.earthquakes.ui.quakes.SearchDTO;

import java.util.Date;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Max
 * on 23-Jun-17.
 */

public interface IRepo {

    void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener);

    void checkNewEarthquakes(IQuakesCallbackListener listener);

    RealmResults<Quake> getAllQuakes(SearchDTO searchParams);

    void closeRealm();

    void saveLastUpdate(long time);

    void saveToRealm(List<QuakeData> quakes);
}
