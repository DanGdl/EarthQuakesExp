package com.dgd.earthquakes.data;

import android.text.TextUtils;

import com.dgd.earthquakes.BaseApplication;
import com.dgd.earthquakes.data.network.INetworkManager;
import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.data.prefs.SharedPrefsManager;
import com.dgd.earthquakes.models.Quake;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Max on 23-Jun-17.
 */

public class Repo implements IRepo {
    private INetworkManager mNetwork;
    private Realm realm;

    public Repo() {
        mNetwork = new NetworkManager();

        openRealm();
    }

    private void openRealm() {
        if(realm != null){
            return;
        }

        Realm.init(BaseApplication.getInstance());
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(config);
    }

    @Override
    public void closeRealm() {
        if(realm != null && !realm.isClosed()){
            realm.removeAllChangeListeners();
            realm.close();
            realm = null;
        }
    }

    @Override
    public void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener) {
        mNetwork.getEarthquakes(start, end, listener);
    }

    @Override
    public void checkNewEarthquakes(IQuakesCallbackListener listener) {
        mNetwork.checkNewEarthquakes(listener);
    }

    @Override
    public RealmResults<Quake> getAllQuakes(String query) {
        openRealm();
        RealmQuery<Quake> q = realm.where(Quake.class);
        if(!TextUtils.isEmpty(query)){
            q.contains("title", query);
        }
        return q.findAllSorted("date", Sort.DESCENDING);
    }



    @Override
    public void saveLastUpdate(long time) {
        SharedPrefsManager.getInstance().saveLastUpdateDate(time);
    }

    @Override
    public void saveToRealm(List<QuakeData> quakes) {
        openRealm();
        realm.beginTransaction();
        for(QuakeData qd:quakes){
            realm.insertOrUpdate(qd.fillQuake(new Quake()));
        }
        realm.commitTransaction();
    }
}
