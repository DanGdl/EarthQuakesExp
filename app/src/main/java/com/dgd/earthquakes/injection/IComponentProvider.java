package com.dgd.earthquakes.injection;

import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.database.IDataBase;
import com.dgd.earthquakes.data.network.INetworkManager;
import com.dgd.earthquakes.data.prefs.IPrefs;

/**
 * Created by Owner
 * on 27/06/2018.
 */
public interface IComponentProvider {
    IRepo getRepository();

    INetworkManager getNetwork();

    IPrefs getPrefs();

    IDataBase getQuakesDataBase();
}
