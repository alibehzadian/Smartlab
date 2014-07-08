package com.example.databasetest.data;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteClosable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAdapter {

	private final String TAG = "DatabaseAdapter";

	private DatabaseOpenHelper openHelper;

	public static final String TBL_BOOKS = "books";
	public static final String BOOK_ID = "_id";
	public static final String BOOK_NAME = "_name";
	public static final String BOOK_PUBLISH_DATE = "_publish_date";
	public static final String BOOK_PUBLISHER = "_publisher";
	public static final String BOOK_AUTHOR = "_author";
	public static final String BOOK_ISBN = "_isbn";

	public DatabaseAdapter(Context context) {
		openHelper = new DatabaseOpenHelper(context, "books.db", null, 1);
	}

	public Long insertBook(Book book) {
		SQLiteDatabase db = null;
		Long id = -1L;
		try {
			ContentValues values = new ContentValues();
			values.put(BOOK_NAME, book.getName());
			values.put(BOOK_PUBLISH_DATE, book.getPublishDate().getTime());
			values.put(BOOK_PUBLISHER, book.getPublisher());
			values.put(BOOK_AUTHOR, book.getAuthor());
			values.put(BOOK_ISBN, book.getIsbn());

			db = openHelper.getWritableDatabase();
			id = db.insert(TBL_BOOKS, null, values);
		} catch (Exception e) {
			Log.e(TAG, "Exception: " + e.getMessage());
		} finally {
			db.close();
		}
		return id;
	}

	public int deleteBook(long id) {
		SQLiteDatabase db = null;
		int count = -1;

		try {
			db = openHelper.getWritableDatabase();
			count = db.delete(TBL_BOOKS, BOOK_ID + "=?",
					new String[] { String.valueOf(id) });
		} catch (Exception e) {
			Log.e(TAG, "Exception: " + e.getMessage());
		} finally {
			db.close();
		}
		return count;
	}

	public int updateBook(Book book) {
		SQLiteDatabase db = null;
		int count = -1;

		try {
			db = openHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(BOOK_NAME, book.getName());
			values.put(BOOK_PUBLISH_DATE, book.getPublishDate().getTime());
			values.put(BOOK_PUBLISHER, book.getPublisher());
			values.put(BOOK_AUTHOR, book.getAuthor());
			values.put(BOOK_ISBN, book.getIsbn());
			count = db.update(TBL_BOOKS, values, BOOK_ID + "=?",
					new String[] { String.valueOf(book.getId()) });
		} catch (Exception e) {
			Log.e(TAG, "Exception: " + e.getMessage());
		} finally {
			db.close();
		}
		return count;
	}

	public ArrayList<Book> getBooks() {
		ArrayList<Book> result = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;

		try {
			db = openHelper.getWritableDatabase();
			
			cursor = db.query(TBL_BOOKS, new String[] { "*" }, null, null,
					null, null, null);
			
			// Second Way:
			// String sql = "select * from " + TBL_BOOKS;
			// cursor = db.rawQuery(sql, null);
			
			if (cursor.moveToFirst()) {
				result = new ArrayList<Book>();
				do {
					result.add(extractBook(cursor));
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.e(TAG, "Exception: " + e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			db.close();
		}
		return result;
	}

	private Book extractBook(Cursor cursor) {
		Book book = new Book();

		book.setId(cursor.getLong(cursor.getColumnIndex(BOOK_ID)));
		book.setName(cursor.getString(cursor.getColumnIndex(BOOK_NAME)));
		book.setPublisher(cursor.getString(cursor.getColumnIndex(BOOK_PUBLISHER)));
		book.setAuthor(cursor.getString(cursor.getColumnIndex(BOOK_AUTHOR)));
		book.setIsbn(cursor.getString(cursor.getColumnIndex(BOOK_ISBN)));
		book.setPublishDate(new Date(cursor.getLong(cursor.getColumnIndex(BOOK_PUBLISH_DATE))));

		return book;
	}

	// ================================================

	class DatabaseOpenHelper extends SQLiteOpenHelper {

		public DatabaseOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String query = String
					.format("create table %s (%s integer primary key, %s text, %s text, %s integer, %s text, %s text)",
							TBL_BOOKS, BOOK_ID, BOOK_AUTHOR, BOOK_NAME,
							BOOK_PUBLISH_DATE, BOOK_PUBLISHER, BOOK_ISBN);
			db.execSQL(query);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}

	}

}
