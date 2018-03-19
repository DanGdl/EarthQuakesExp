package com.dgd.earthquakes.models;

import java.util.Date;

public class Quake implements IQuake{

	private String id;
	private Date date;
	private double latitude;
	private double longitude;
	private String magnitude;
	private String link;
	private String title;

	public Date getDate() {
		return date;
	}

	public void setDate(Date mDate) {
		this.date = mDate;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(String mMagnitude) {
		if(mMagnitude != null && mMagnitude.length() < 4){
			mMagnitude += "0";
		}
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
