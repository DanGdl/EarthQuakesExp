package com.dgd.earthquakes.models;

import android.location.Location;

import java.util.Date;

/**
 * Created by Max
 * on 22-Jun-17.
 */

public interface IQuake {

    Date getDate();

    void setDate(Date mDate);

    Location getLocation();

    void setLocation(Location mLocation);

    String getMagnitude();

    void setMagnitude(String mMagnitude);

    String getLink();

    void setLink(String mLink);

    String getId();

    void setId(String mId);

    String getTitle();

    void setTitle(String mTitle);
}
