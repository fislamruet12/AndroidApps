package com.example.dailynote;

import java.util.Calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "dailynote";
	public static final int VERSION = 1;
// earn table
	public static final String EARN="earn";
	public static final String COST="cost";
	public static final String LOAN="loan";
	public static final String ADVANCE="advance";

	 //EARN TABLE
	public  static final String E_ID="e_id";
	public  static final String E_TAKA="e_taka";
	public  static final String E_DATE="e_date";

	//COST TABLE

		//public  static final String C_ID="c_id";
		public static final String C_TAKA="c_taka";
		public  static final String C_DATE="c_date";
		// LOAN TABLE


	//	public  static final String L_ID="l_id";
		public  static final String L_NAME="l_name";
		public  static final String L_TAKA="l_taka";
		public  static final String L_DATE="l_date";


		// advance

		//public  static final String A_ID="a_id";
		public  static final String A_NAME="a_name";
		public  static final String A_TAKA="a_taka";
		public  static final String A_DATE="a_date";

	public static final String TABLE_EARN = "CREATE TABLE " + EARN + " ("
			+ E_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + E_TAKA
			+ " TEXT, " + E_DATE + " INTEGER)";

	public static final String TABLE_COST = "CREATE TABLE " + COST + " ("
			+ E_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C_TAKA
			+ " TEXT, " + C_DATE + " INTEGER)";
	

	public static final String TABLE_LOAN = "CREATE TABLE " + LOAN + " ("
			+ E_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ L_NAME + " TEXT, " + L_TAKA + " TEXT, " + L_DATE + " INTEGER)";
	
	public static final String TABLE_ADVANCE = "CREATE TABLE " + ADVANCE + " ("
			+ E_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ A_NAME + " TEXT, " + A_TAKA + " TEXT, " + A_DATE + " INTEGER)";
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("TABLE SQL", TABLE_EARN);
		db.execSQL(TABLE_EARN);
		Log.d("TABLE SQL", TABLE_COST);
		db.execSQL(TABLE_COST);
		Log.d("TABLE SQL", TABLE_LOAN);
		db.execSQL(TABLE_LOAN);
	Log.d("TABLE SQL", TABLE_ADVANCE);
		db.execSQL(TABLE_ADVANCE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// UPGRADE LOGIC

	}

}