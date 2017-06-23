package com.dgd.earthquakes.network.callback;

import com.dgd.earthquakes.network.infra.QuakeData;

import java.util.List;

/**
 * Created by Max on 30-Apr-17.
 */
public interface IQuakesCallbackListener {
    void onNetworkError(String errorMessage, int errorCode);
    void onNetworkSuccess(List<QuakeData> quakes);
}
