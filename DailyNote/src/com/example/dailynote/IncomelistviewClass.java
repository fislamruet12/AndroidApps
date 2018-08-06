package com.example.dailynote;

import java.util.Calendar;

public class IncomelistviewClass {
	
	private String id;
	private String taka;
	private Calendar date;
	private int check;
	public IncomelistviewClass(String id, String taka, Calendar date, int check) {
		super();
		this.id = id;
		this.taka = taka;
		this.date = date;
		this.check = check;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	
}