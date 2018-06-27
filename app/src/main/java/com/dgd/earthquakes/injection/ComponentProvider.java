package com.dgd.earthquakes.injection;

import android.content.Context;

import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.Repo;
import com.dgd.earthquakes.data.database.IDataBase;
import com.dgd.earthquakes.data.database.SQLiteManager;
import com.dgd.earthquakes.data.network.INetworkManager;
import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.data.prefs.IPrefs;
import com.dgd.earthquakes.data.prefs.SharedPrefsManager;

import java.lang.ref.WeakReference;

/**
 * Created by Owner
 * on 27/06/2018.
 */
public class ComponentProvider implements IComponentProvider {
    private final Context appCtx;
    private WeakReference<IRepo> repoRef;
    private WeakReference<INetworkManager> networkRef;
    private WeakReference<IPrefs> prefsRef;
    private WeakReference<IDataBase> dbRef;

    public ComponentProvider(Context appCtx) {
        this.appCtx = appCtx;
    }

    @Override
    public IRepo getRepository() {
        if(repoRef == null || repoRef.get() == null){
            repoRef = new WeakReference<>(new Repo(getNetwork(), getQuakesDataBase(), getPrefs()));
        }
        return repoRef.get();
    }

    @Override
    public INetworkManager getNetwork() {
        if(networkRef == null || networkRef.get() == null){
            networkRef = new WeakReference<>(new NetworkManager());
        }
        return networkRef.get();
    }

    @Override
    public IPrefs getPrefs() {
        if(prefsRef == null || prefsRef.get() == null){
            prefsRef = new WeakReference<>(new SharedPrefsManager(appCtx));
        }
        return prefsRef.get();
    }

    @Override
    public IDataBase getQuakesDataBase() {
        if(dbRef == null || dbRef.get() == null){
            dbRef = new WeakReference<>(new SQLiteManager(appCtx));
        }
        return dbRef.get();
    }
}
