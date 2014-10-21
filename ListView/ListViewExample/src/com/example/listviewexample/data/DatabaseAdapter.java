package com.example.listviewexample.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {

	DatabaseOpenHelper helper;

	public DatabaseAdapter(Context context) {
		helper = new DatabaseOpenHelper(context, "books_db.db", null, 1);
	}

	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> result = null;
		SQLiteDatabase db = null;
		Cursor c = null;

		try {

			db = helper.getReadableDatabase();
			c = db.query("BOOKS", new String[] { "*" }, null, null, null, null,
					null);

			if (c.moveToFirst()) {
				result = new ArrayList<Book>();
				do {
					Long id = c.getLong(c.getColumnIndex("ID"));
					String name = c.getString(c.getColumnIndex("NAME"));
					String author = c.getString(c.getColumnIndex("AUTHOR"));
					result.add(new Book(id, name, author));
				} while (c.moveToNext());
			}

		} catch (Exception e) {
			
		} finally {
			if (db.isOpen()) {
				db.close();
			}
			if (c != null) {
				c.close();
			}
		}

		return result;
	}

	class DatabaseOpenHelper extends SQLiteOpenHelper {

		public DatabaseOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table BOOKS (ID integer primary key, NAME text, AUTHOR text)");

			ContentValues cv = new ContentValues();
			for (int i = 0; i < 20; i++) {
				cv.put("NAME", "NAME " + i);
				cv.put("AUTHOR", "AUTHOR " + i);
				db.insert("BOOKS", null, cv);
				cv.clear();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}

	}

}
