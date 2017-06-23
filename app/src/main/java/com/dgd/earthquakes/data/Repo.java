package com.dgd.earthquakes.data;

import com.dgd.earthquakes.BaseApplication;
import com.dgd.earthquakes.R;
import com.dgd.earthquakes.data.network.INetworkManager;
import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.models.Quake;
import com.dgd.earthquakes.screens.activity.BaseActivity;
import com.dgd.earthquakes.util.SharedPrefsManager;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Max on 23-Jun-17.
 */

public class Repo implements IRepo {
    private INetworkManager mNetwork;
    private Realm realm;

    public Repo() {
        mNetwork = NetworkManager.getInstance();

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
    public void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener) {

    }

    @Override
    public void checkNewEarthquakes() {
        mNetwork.checkNewEarthquakes(new IQuakesCallbackListener() {
            @Override
            public void onNetworkError(String errorMessage, int errorCode) {
                BaseActivity activity = getCurrentActivity();
                if(activity != null) {
                    activity.showMessage(activity.getString(R.string.shit, errorMessage));
                    activity.hideProgress();
                }
            }

            @Override
            public void onNetworkSuccess(List<QuakeData> quakes) {
                SharedPrefsManager.getInstance().saveLastUpdateDate(new Date().getTime());
                openRealm();
                realm.beginTransaction();
                for(QuakeData qd:quakes){
                    Quake q = realm.createObject(Quake.class);
                    qd.fillQuake(q);
                }
                realm.commitTransaction();

                BaseActivity activity = getCurrentActivity();
                if(activity != null) {
                    activity.hideProgress();
                }
            }
        });
    }

    @Override
    public void getEarthquakes() {

    }

    @Override
    public RealmResults<Quake> getAllQuakes() {
        openRealm();
        return realm.where(Quake.class).findAllSorted("date", Sort.DESCENDING);
    }

    @Override
    public void closeRealm() {
        if(realm != null && !realm.isClosed()){
            realm.removeAllChangeListeners();
            realm.close();
            realm = null;
        }
    }

    private BaseActivity getCurrentActivity(){
        return BaseApplication.getInstance().getCurrentActivity();
    }
}
