package com.dgd.earthquakes.models;

import java.util.Date;

/**
 * Created by Max on 22-Jun-17.
 */

public interface IQuake {

    Date getDate();

    void setDate(Date mDate);

    double getLatitude();

    void setLatitude(double latitude);

    double getLongitude();

    void setLongitude(double longitude);

    String getMagnitude();

    void setMagnitude(String mMagnitude);

    String getLink();

    void setLink(String mLink);

    String getId();

    void setId(String mId);

    String getTitle();

    void setTitle(String mTitle);
}
