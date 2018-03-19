package com.dgd.earthquakes.data.network.infra;

import com.dgd.earthquakes.models.Quake;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Max
 * on 01-May-17.
 */
public class QuakeData {

    @Expose
    @SerializedName("id")
    private String mId;

    @Expose
    @SerializedName("properties")
    private Properties mProperties;

    @Expose
    @SerializedName("geometry")
    private Geometry mGeometry;

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public Properties getProperties() {
        return mProperties;
    }

    public void setProperties(Properties mProperties) {
        this.mProperties = mProperties;
    }

    public Geometry getGeometry() {
        return mGeometry;
    }

    public void setGeometry(Geometry mGeometry) {
        this.mGeometry = mGeometry;
    }

    public Quake fillQuake(Quake q) {
        q.setTitle(mProperties.getTitle());
        q.setDate(new Date(mProperties.getTime()));
        q.setId(mId);
        q.setLink(mProperties.getUrl());
        q.setMagnitude("" + mProperties.getMagnitude());

        List<Double> coordinates = mGeometry.getCoordinates();
        q.setLatitude(coordinates.get(1));
        q.setLongitude(coordinates.get(0));
        return q;
    }
}
