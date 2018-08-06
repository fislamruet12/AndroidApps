package com.example.countingkazanamaz;


import java.util.ArrayList;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	private DBHelper dbHelper;
	private Context context;
	private SQLiteDatabase db;

	public DBAdapter(Context context) {
		this.context = context;
		dbHelper = new DBHelper(context);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		db.close();
	}

	public long insertBook(Book book) {
		ContentValues values = new ContentValues();
		values.put(DBHelper.TITLE, book.getTitle());
		values.put(DBHelper.AUTHOR, book.getAuthor());
		values.put(DBHelper.CATEGORY, book.getCategory());
		values.put(DBHelper.ISBN, book.getISBN());
		values.put(DBHelper.PRICE, book.getPrice());

		long inserted = db.insert(DBHelper.TABLE_NAME, null, values);

		return inserted;
	}

	public void deleteBook(String st,String st1) {
     SQLiteDatabase db=dbHelper.getWritableDatabase();
     //db.execSQL("delete from "+DBHelper.TABLE_NAME+"where"+DBHelper.TITLE+"="+st);
     db.delete(DBHelper.TABLE_NAME,DBHelper.TITLE+"="+st,null);
     db.close();
	}
     public int getQuary(String st,String st1)
     {

    	 Cursor cursor=db.rawQuery("select count(*) from books where title = ? and author = ?",new String[]{st,st1});
    	 
    	 int x=0;
    	 if(cursor.moveToFirst())
    	 {
    		 x=cursor.getInt(0);
    		 
    	 }
    	 
    	 cursor.close();
    	 return x;
     }
     
     public void delQuary(String st,String st1)
     {

    	 Cursor cursor=db.rawQuery("select max(_id) from books where title = ? and author = ?",new String[]{st,st1});
    	 
    	int x=0;
    	 if(cursor.moveToFirst())
    	 {
        x=cursor.getInt(0);
    		 
    	 }
    	 
    	 db.delete(DBHelper.TABLE_NAME,DBHelper.ID_FIELD+"="+x,null);
         db.close(); 
        
    	
     }
	public ArrayList<Book> getAllBooks() {
		try {
			ArrayList<Book> allBooks = null;
		//	String[] columns = { DBHelper.TITLE, DBHelper.AUTHOR };

			Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
					null, null);
			// select * from books;
			if (cursor != null && cursor.getCount() > 0) {
				int size = cursor.getCount();
				cursor.moveToFirst();
				allBooks = new ArrayList<Book>();
				for (int i = 0; i < size; i++) {
					String title = cursor.getString(cursor
							.getColumnIndex(DBHelper.TITLE));
					String author = cursor.getString(cursor
							.getColumnIndex(DBHelper.AUTHOR));
					String category = cursor.getString(cursor
							.getColumnIndex(DBHelper.CATEGORY));
					String ISBN = cursor.getString(cursor
							.getColumnIndex(DBHelper.ISBN));
					double price = cursor.getDouble(cursor
							.getColumnIndex(DBHelper.PRICE));
					allBooks.add(new Book(title, author, category, ISBN, price));
					cursor.moveToNext();
				}
			}

			cursor.close();

			return allBooks;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	
	
}