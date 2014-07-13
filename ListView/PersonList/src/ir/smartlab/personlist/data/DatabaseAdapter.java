package ir.smartlab.personlist.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAdapter {
	
	private final String TABLE_NAME = "TBL_PERSONS";
	private final String COL_ID = "_ID";
	private final String COL_NAME = "_NAME";
	private final String COL_FAMILY = "_FAMILY";
	private final String COL_BIRTHDATE = "_BIRTHDATE";
	
	SQLiteDatabase database;
	
	public DatabaseAdapter(Context context) {
		MyDatabaseOpenHelper openHelper = new MyDatabaseOpenHelper(context, "persons.db", null, 1);
		database = openHelper.getWritableDatabase();
	}
	
	public List<Person> getPersons() {
		List<Person> persons = null;
		String sql = "select * from " + TABLE_NAME;
		Cursor c = null;
		try {
			c = database.rawQuery(sql, null);
			if( c.moveToFirst() ) {
				persons = new ArrayList<Person>();
				do {
					Person p = extractPerson(c);
					persons.add(p);
				} while(c.moveToNext());
			}
		} catch (Exception e) {
			Log.d("DATABASE", e.getMessage());
		} finally {
			try {
				if( c != null && !c.isClosed()) {
					c.close();
				}
			} catch (Exception e2) {
			}
		}
		return persons;
	}
	
	private Person extractPerson(Cursor c) {
		Person person = new Person();
		person.setId(c.getLong(c.getColumnIndex(COL_ID)));
		person.setName(c.getString(c.getColumnIndex(COL_NAME)));
		person.setFamily(c.getString(c.getColumnIndex(COL_FAMILY)));
		person.setBirthdate(new Date(c.getLong(c.getColumnIndex(COL_BIRTHDATE))));
		return person;
	}

	public long insertPerson(Person person) {
		ContentValues cv = new ContentValues();
		cv.put(COL_NAME, person.getName());
		cv.put(COL_FAMILY, person.getFamily());
		cv.put(COL_BIRTHDATE, person.getBirthdate().getTime());
		long id = database.insert(TABLE_NAME, null, cv); 
		return id;
	}
	
	public int deletePerson(long id) {
		return database.delete(TABLE_NAME, COL_ID + "=?", new String[] {String.valueOf(id)}); 
	}
	
	public int updatePerson(Person person) {
		ContentValues cv = new ContentValues();
		cv.put(COL_NAME, person.getName());
		cv.put(COL_FAMILY, person.getFamily());
		cv.put(COL_BIRTHDATE, person.getBirthdate().getTime());
		return database.update(TABLE_NAME, cv, COL_ID + "=?", new String[] {String.valueOf(person.getId())});
	}
	
	public class MyDatabaseOpenHelper extends SQLiteOpenHelper {

		public MyDatabaseOpenHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = String.format("create table %s (%s integer primary key, %s text, %s text, %s integer)", TABLE_NAME, COL_ID, COL_NAME, COL_FAMILY, COL_BIRTHDATE);
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		}
		
	}

}
