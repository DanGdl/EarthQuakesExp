package com.dgd.earthquakes.data;

import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;

import java.util.Date;

/**
 * Created by Max on 23-Jun-17.
 */

public class Repo implements IRepo {
    private static final Repo ourInstance = new Repo();

    public static Repo getInstance() {
        return ourInstance;
    }

    private Repo() {}


    @Override
    public void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener) {

    }

    @Override
    public void checkNewEarthquakes(IQuakesCallbackListener listener) {
        NetworkManager.getInstance().checkNewEarthquakes(listener);
    }

    @Override
    public void getEarthquakes() {

    }
}
