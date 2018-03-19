package com.dgd.earthquakes.data;

import com.dgd.earthquakes.data.database.SQLiteManager;
import com.dgd.earthquakes.data.network.INetworkManager;
import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
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
    private INetworkManager mNetwork;
    private SQLiteManager realm;

    public Repo() {
        mNetwork = new NetworkManager();

        openRealm();
    }

    private void openRealm() {
        if(realm != null){
            return;
        }

        realm = SQLiteManager.getInstance();
    }

    @Override
    public void closeRealm() {
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
    public List<Quake> getAllQuakes(SearchDTO searchParams) {
        openRealm();
//        RealmQuery<Quake> q = realm.where(Quake.class);
//        // todo Dan: impl search by other fields
//        if(!TextUtils.isEmpty(searchParams.query)){
//            q.contains("title", searchParams.query);
//        }
//        return q.findAllSorted("date", Sort.DESCENDING);
        return new ArrayList<>();
    }



    @Override
    public void saveLastUpdate(long time) {
        SharedPrefsManager.getInstance().saveLastUpdateDate(time);
    }

    @Override
    public void saveToRealm(List<QuakeData> quakes) {
        realm.saveQuakes(quakes);
    }
}
