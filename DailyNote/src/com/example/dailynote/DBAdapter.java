package com.example.dailynote;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
 private DBHelper dbHelper;
 private Context context;
private SQLiteDatabase db;
public DBAdapter(Context context)
{
	this.context=context;
	dbHelper=new DBHelper(context);
}
public void open()
{
	db=dbHelper.getWritableDatabase();
		
}
public void  close()
{
	db.close();
}
  public long EarnInsert(EarnAndCost earnAndCost)
  {
	  
	 ContentValues values=new ContentValues();
	 values.put(DBHelper.E_TAKA,earnAndCost.getDbtaka());
	 values.put(DBHelper.E_DATE,earnAndCost.getDbcal());
	long insert= db.insert(DBHelper.EARN,null, values);
	 
	 return insert; 
  }
  public long CostInsert(EarnAndCost earnAndCost)
  {
	  
	  
		 ContentValues values=new ContentValues();
		 values.put(DBHelper.C_TAKA,earnAndCost.getDbtaka());
		 values.put(DBHelper.C_DATE,earnAndCost.getDbcal());
		long insert= db.insert(DBHelper.COST,null, values);
		 
		 return insert;
  }
  
  public long LoanInsert(LoanAndSave loanAndSave)
  {
	  ContentValues values=new ContentValues();
	  values.put(DBHelper.L_NAME,loanAndSave.getName());
	  values.put(DBHelper.L_TAKA,loanAndSave.getTaka());
	  values.put(DBHelper.L_DATE,loanAndSave.getDate());
	  long ins=db.insert(DBHelper.LOAN,null, values);
	  
	  return ins;
	  
  }
  public long AdvanceIsert(LoanAndSave loanAndSave)
  {
	  ContentValues values=new ContentValues();
	  values.put(DBHelper.A_NAME,loanAndSave.getName());
	  values.put(DBHelper.A_TAKA,loanAndSave.getTaka());
	  values.put(DBHelper.A_DATE,loanAndSave.getDate());
	  long ins=db.insert(DBHelper.ADVANCE,null, values);
	  
	  return ins;
	  
  }
  
  public ArrayList<GetidEarnAndCost> getAllCost() {
		try {
			ArrayList<GetidEarnAndCost> allearn = null;
		//	String[] columns = { DBHelper.TITLE, DBHelper.AUTHOR };
           String queryString="SELECT * FROM "+DBHelper.COST;
			Cursor cursor = db.rawQuery(queryString,null);
			// select * from books;
			if (cursor != null && cursor.getCount() > 0) {
				int size = cursor.getCount();
				cursor.moveToFirst();
				allearn = new ArrayList<GetidEarnAndCost>();
				for (int i = 0; i < size; i++) {

					String id = cursor.getString(cursor
							.getColumnIndex(DBHelper.E_ID));
					String taka = cursor.getString(cursor
							.getColumnIndex(DBHelper.C_TAKA));
					String date = cursor.getString(cursor
							.getColumnIndex(DBHelper.C_DATE));
						allearn.add(new GetidEarnAndCost(id,date,taka));
					cursor.moveToNext();
				}
			}

			cursor.close();

			return allearn;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}

  
  public ArrayList<GetidEarnAndCost> getAllEarn() {
		try {
			ArrayList<GetidEarnAndCost> allearn = null;
		//	String[] columns = { DBHelper.TITLE, DBHelper.AUTHOR };
         String queryString="SELECT * FROM "+DBHelper.EARN;
			Cursor cursor = db.rawQuery(queryString,null);
			// select * from books;
			if (cursor != null && cursor.getCount() > 0) {
				int size = cursor.getCount();
				cursor.moveToFirst();
				allearn = new ArrayList<GetidEarnAndCost>();
				for (int i = 0; i < size; i++) {

					String id = cursor.getString(cursor
							.getColumnIndex(DBHelper.E_ID));
					String taka = cursor.getString(cursor
							.getColumnIndex(DBHelper.E_TAKA));
					String date = cursor.getString(cursor
							.getColumnIndex(DBHelper.E_DATE));
						allearn.add(new GetidEarnAndCost(id,date,taka));
					cursor.moveToNext();
				}
			}

			cursor.close();

			return allearn;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
  
  
  
  public ArrayList<DbInsertingClass> getAllLoan() {
		try {
			ArrayList<DbInsertingClass> allearn = null;
		//	String[] columns = { DBHelper.TITLE, DBHelper.AUTHOR };
           String queryString="SELECT * FROM "+DBHelper.LOAN;
			Cursor cursor = db.rawQuery(queryString,null);
			// select * from books;
			if (cursor != null && cursor.getCount() > 0) {
				int size = cursor.getCount();
				cursor.moveToFirst();
				allearn = new ArrayList<DbInsertingClass>();
				for (int i = 0; i < size; i++) {
                     String id = cursor.getString(cursor
        							.getColumnIndex(DBHelper.E_ID));
                     
					String name = cursor.getString(cursor
							.getColumnIndex(DBHelper.L_NAME));
					String taka = cursor.getString(cursor
							.getColumnIndex(DBHelper.L_TAKA));
					String date = cursor.getString(cursor
							.getColumnIndex(DBHelper.L_DATE));
						allearn.add(new DbInsertingClass(id,name,taka,date));
					cursor.moveToNext();
				}
			}

			cursor.close();

			return allearn;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
  public ArrayList<DbInsertingClass> getAllAdvance() {
		try {
			ArrayList<DbInsertingClass> allearn = null;
		//	String[] columns = { DBHelper.TITLE, DBHelper.AUTHOR };
           String queryString="SELECT * FROM "+DBHelper.ADVANCE;
			Cursor cursor = db.rawQuery(queryString,null);
			// select * from books;
			if (cursor != null && cursor.getCount() > 0) {
				int size = cursor.getCount();
				cursor.moveToFirst();
				allearn = new ArrayList<DbInsertingClass>();
				for (int i = 0; i < size; i++) {

					String id = cursor.getString(cursor
							.getColumnIndex(DBHelper.E_ID));
					String name = cursor.getString(cursor
							.getColumnIndex(DBHelper.A_NAME));
					String taka = cursor.getString(cursor
							.getColumnIndex(DBHelper.A_TAKA));
					String date = cursor.getString(cursor
							.getColumnIndex(DBHelper.A_DATE));
						allearn.add(new DbInsertingClass(id,name,taka,date));
					cursor.moveToNext();
				}
			}

			cursor.close();

			return allearn;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
  
     public void DeleteFromDb(String Dbname,long id)
     {
    	 db.delete(Dbname,DBHelper.E_ID+" = ?",new String[]{String.valueOf(id)});
    	 
     }
     public void DeletebydateE(String Dbname,long id)
     {
    	 db.delete(Dbname,DBHelper.E_DATE+" = ?",new String[]{String.valueOf(id)});
    	 
     }
     public void DeletebydateC(String Dbname,long id)
     {
    	 db.delete(Dbname,DBHelper.C_DATE+" = ?",new String[]{String.valueOf(id)});
    	 
     }
     public void updateDbl(String TableName,String val,long id)
     {
    	 ContentValues values =new ContentValues();
    	 
    	 values.put(DBHelper.L_TAKA,val);
    	 db.update(TableName, values, DBHelper.E_ID+" = ?",new String[]{String.valueOf(id)});
    	 
     }
     public void updateDbA(String TableName,String val,long id)
     {
    	 ContentValues values =new ContentValues();
    	 
    	 values.put(DBHelper.A_TAKA,val);
    	 db.update(TableName, values, DBHelper.E_ID+" = ?",new String[]{String.valueOf(id)});
    	 
     }

}
