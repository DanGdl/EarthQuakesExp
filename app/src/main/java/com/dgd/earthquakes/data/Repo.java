package com.dgd.earthquakes.data;

import com.dgd.earthquakes.data.database.IDataBase;
import com.dgd.earthquakes.data.database.SQLiteManager;
import com.dgd.earthquakes.data.network.INetworkManager;
import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.data.prefs.IPrefs;
import com.dgd.earthquakes.data.prefs.SharedPrefsManager;
import com.dgd.earthquakes.models.Quake;
import com.dgd.earthquakes.ui.quakes.SearchDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Max
 * on 23-Jun-17.
 */

public class Repo implements IRepo {
    private final IPrefs prefs;
    private final INetworkManager network;
    private final IDataBase dataBase;

    public Repo(INetworkManager network, IDataBase dataBase, IPrefs prefs) {
        this.network = network;
        this.dataBase = dataBase;
        this.prefs = prefs;
    }

    @Override
    public void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener) {
        network.getEarthquakes(start, end, listener);
    }

    @Override
    public void checkNewEarthquakes(IQuakesCallbackListener listener) {
        network.checkNewEarthquakes(listener);
    }

    @Override
    public List<Quake> getAllQuakes(SearchDTO searchParams) {
//        RealmQuery<Quake> q = dataBase.where(Quake.class);
//        // todo Dan: impl search by other fields
//        if(!TextUtils.isEmpty(searchParams.query)){
//            q.contains("title", searchParams.query);
//        }
//        return q.findAllSorted("date", Sort.DESCENDING);
        return new ArrayList<>();
    }



    @Override
    public void saveLastUpdate(long time) {
        prefs.saveLastUpdateDate(time);
    }

    @Override
    public void save(List<QuakeData> quakes) {
        dataBase.saveQuakes(quakes);
    }
}
