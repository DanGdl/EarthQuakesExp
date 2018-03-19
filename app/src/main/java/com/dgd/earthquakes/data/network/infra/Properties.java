package com.dgd.earthquakes.data.network.infra;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Max
 * on 01-May-17.
 */

public class Properties {

    @Expose
    @SerializedName("title")
    private String mTitle;

    @Expose
    @SerializedName("url")
    private String mUrl;

    @Expose
    @SerializedName("mag")
    private float mMagnitude;

    @Expose
    @SerializedName("time")
    private long mTime;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public float getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(float mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long mTime) {
        this.mTime = mTime;
    }
}
