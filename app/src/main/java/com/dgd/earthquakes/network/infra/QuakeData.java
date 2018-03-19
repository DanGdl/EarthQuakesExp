package com.dgd.earthquakes.network.infra;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
}
