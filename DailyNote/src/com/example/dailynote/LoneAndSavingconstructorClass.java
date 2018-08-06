package com.example.dailynote;

import java.util.Calendar;

public class LoneAndSavingconstructorClass {
	private String id;
	private String name;
	private String taka;
	private Calendar date;
	public LoneAndSavingconstructorClass(String id, String name, String taka,
			Calendar date) {
		super();
		this.id = id;
		this.name = name;
		this.taka = taka;
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTaka() {
		return taka;
	}
	public void setTaka(String taka) {
		this.taka = taka;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	
}