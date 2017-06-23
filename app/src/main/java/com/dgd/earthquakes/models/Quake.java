package com.dgd.earthquakes.models;

import android.location.Location;

import java.util.Date;

public class Quake implements IQuake{

	private Date mDate;
	private Location mLocation;
	private String mMagnitude;
	private String mLink;
	private String mId;
	private String mTitle;

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date mDate) {
		this.mDate = mDate;
	}

	public Location getLocation() {
		return mLocation;
	}

	public void setLocation(Location mLocation) {
		this.mLocation = mLocation;
	}

	public String getMagnitude() {
		return mMagnitude;
	}

	public void setMagnitude(String mMagnitude) {
		this.mMagnitude = mMagnitude;
	}

	public String getLink() {
		return mLink;
	}

	public void setLink(String mLink) {
		this.mLink = mLink;
	}

	public String getId() {
		return mId;
	}

	public void setId(String mId) {
		this.mId = mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
}
