package com.dgd.earthquakes.data.network.infra;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Max on 01-May-17.
 */
public class Geometry {
//    first - lng, second - lat
    @Expose
    @SerializedName("coordinates")
    private List<Double> mCoordinates;

    public List<Double> getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(List<Double> mCoordinates) {
        this.mCoordinates = mCoordinates;
    }
}
