package com.dgd.earthquakes.network;

import com.dgd.earthquakes.network.callback.IQuakesCallbackListener;

import java.util.Date;

/**
 * Created by Max
 * on 23-Jun-17.
 */

public interface INetworkManager {
    void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener);

    void checkNewEarthquakes(IQuakesCallbackListener listener);
}
