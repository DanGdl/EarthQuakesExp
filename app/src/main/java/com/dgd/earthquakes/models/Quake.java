package com.dgd.earthquakes.models;

import android.location.Location;

import java.util.Date;

import io.realm.RealmObject;

public class Quake extends RealmObject implements IQuake{

	private Date date;
	private Location location;
	private String magnitude;
	private String link;
	private String id;
	private String title;

	public Date getDate() {
		return date;
	}

	public void setDate(Date mDate) {
		this.date = mDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location mLocation) {
		this.location = mLocation;
	}

	public String getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(String mMagnitude) {
		this.magnitude = mMagnitude;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String mLink) {
		this.link = mLink;
	}

	public String getId() {
		return id;
	}

	public void setId(String mId) {
		this.id = mId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String mTitle) {
		this.title = mTitle;
	}
}
