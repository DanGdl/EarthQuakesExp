package com.dgd.earthquakes.data;

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;

import java.util.Date;

/**
 * Created by Max on 23-Jun-17.
 */

public interface IRepo {

    void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener);

    void checkNewEarthquakes(IQuakesCallbackListener listener);

    void getEarthquakes();
}
