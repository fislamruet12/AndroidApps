package com.example.countingkazanamaz;

import android.util.Log;

public class GetData {
private static String year,wakt;
private static String yearString,waktString;
String login="log";
public String getYear() {
	Log.d(login,"setbegin ="+year);
	return yearString;
}

public void setYear(String year) {
	GetData.year = year;
	yearString=year;
	Log.d(login,"setbegin ="+year);
}

public String getWakt() {
	return waktString;
}

public void setWakt(String wakt) {
	GetData.wakt = wakt;
	waktString=wakt;
}

public GetData(String year, String wakt) {
	super();
	GetData.year = year;
	GetData.wakt = wakt;
}

}
