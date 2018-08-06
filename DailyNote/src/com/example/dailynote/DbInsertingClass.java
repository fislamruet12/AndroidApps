package com.example.dailynote;

import javax.security.auth.PrivateCredentialPermission;

public class DbInsertingClass {
	private String id;
	private String  name;
	private String taka;
	private String date;
	public DbInsertingClass(String id, String name, String taka, String date) {
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
