package com.example.dailynote;

import android.R.string;

public class GetidEarnAndCost {

	private String id;
	private String date;
	private String taka;
	public GetidEarnAndCost(String id, String date, String taka) {
		super();
		this.id = id;
		this.date = date;
		this.taka = taka;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTaka() {
		return taka;
	}
	public void setTaka(String taka) {
		this.taka = taka;
	}
	
}
