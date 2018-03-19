package com.dgd.earthquakes.network.infra;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max
 * on 30-Apr-17.
 */

public class QuakesResponse {

    @Expose
    @SerializedName("features")
    private List<QuakeData> mQuakes;

    public List<QuakeData> getEarthquakes() {
        if(mQuakes == null){
            return new ArrayList<>();
        }
        return mQuakes;
    }
}
