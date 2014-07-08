package com.example.sqlitetestapp.data;

import java.util.ArrayList;
import java.util.Date;

import com.example.sqlitetestapp.data.model.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {

	private MySQLiteOpenHelper openHelper;

	public DatabaseAdapter(Context context) {
		openHelper = new MySQLiteOpenHelper(context, "sqlite_test_app.db",
				null, 1);
	}

	public ArrayList<Person> getAllPersons() {
		ArrayList<Person> persons = null;

		SQLiteDatabase database = null;
		Cursor c = null;
		try {
			database = openHelper.getReadableDatabase();

			c = database.query("person", new String[] { "id", "name", "family",
					"birthdate" }, null, null, null, null, null);

			if (c.getCount() > 0) {
				persons = new ArrayList<Person>();

				while (c.moveToNext()) {
					Person person = new Person();

					Integer pId = c.getInt(c.getColumnIndex("id"));
					person.setId(pId);

					String name = c.getString(c.getColumnIndex("name"));
					person.setName(name);

					String family = c.getString(c.getColumnIndex("family"));
					person.setFamily(family);

					long birthdate = c.getLong(c.getColumnIndex("birthdate"));
					Date bDate = new Date(birthdate);
					person.setBirthdate(bDate);

					persons.add(person);
				}
			}

		} catch (Exception ex) {
			// TODO
		} finally {
			if (c != null && !c.isClosed()) {
				c.close();
			}
			if (database != null) {
				database.close();
			}
		}

		return persons;
	}

	public Person getPersonById(Integer id) {
		Person person = null;

		SQLiteDatabase database = null;
		Cursor c = null;
		try {
			database = openHelper.getReadableDatabase();

			c = database.query("person", new String[] { "id", "name", "family",
					"birthdate" }, "id=?",
					new String[] { Integer.toString(id) }, null, null, null);

			if (c.moveToNext()) {
				person = new Person();

				Integer pId = c.getInt(c.getColumnIndex("id"));
				person.setId(pId);

				String name = c.getString(c.getColumnIndex("name"));
				person.setName(name);

				String family = c.getString(c.getColumnIndex("family"));
				person.setFamily(family);

				long birthdate = c.getLong(c.getColumnIndex("birthdate"));
				Date bDate = new Date(birthdate);
				person.setBirthdate(bDate);
			}

		} catch (Exception ex) {
			// TODO
		} finally {
			if (c != null && !c.isClosed()) {
				c.close();
			}
			if (database != null) {
				database.close();
			}
		}

		return person;
	}

	public ArrayList<Person> getPersonsYoungerThan(long age) {
		ArrayList<Person> persons = null;

		SQLiteDatabase database = null;
		Cursor c = null;
		try {
			database = openHelper.getReadableDatabase();

			c = database.query("person", new String[] { "id", "name", "family",
					"birthdate" }, "birthdate > ?", new String[] {Long.toString(age)}, null, null, "name");

			if (c.getCount() > 0) {
				persons = new ArrayList<Person>();

				while (c.moveToNext()) {
					Person person = new Person();

					Integer pId = c.getInt(c.getColumnIndex("id"));
					person.setId(pId);

					String name = c.getString(c.getColumnIndex("name"));
					person.setName(name);

					String family = c.getString(c.getColumnIndex("family"));
					person.setFamily(family);

					long birthdate = c.getLong(c.getColumnIndex("birthdate"));
					Date bDate = new Date(birthdate);
					person.setBirthdate(bDate);

					persons.add(person);
				}
			}

		} catch (Exception ex) {
			// TODO
		} finally {
			if (c != null && !c.isClosed()) {
				c.close();
			}
			if (database != null) {
				database.close();
			}
		}

		return persons;
	}

	public int deletePerson(Integer id) {
		SQLiteDatabase database = null;
		int count = -1;
		
		try {
			database = openHelper.getWritableDatabase();
			count = database.delete("person", "id=?", new String[] {Integer.toString(id)});
		} catch (Exception ex) {
			// TODO
		} finally {
			database.close();
		}

		return count;
	}

	public int updatePerson(Person person) {
		SQLiteDatabase database = null;
		int count = -1;
		
		try {
			database = openHelper.getWritableDatabase();

			ContentValues cv = new ContentValues();
			cv.put("name", person.getName());
			cv.put("family", person.getFamily());
			cv.put("birthdate", person.getBirthdate().getTime());

			count = database.update("person", cv, "id=?", new String[] {Integer.toString(person.getId())});
		} catch (Exception ex) {
			// TODO
		} finally {
			database.close();
		}

		return count;
	}

	public Integer savePerson(Person person) {
		SQLiteDatabase database = null;
		int id = -1;

		try {
			database = openHelper.getWritableDatabase();

			ContentValues cv = new ContentValues();
			cv.put("name", person.getName());
			cv.put("family", person.getFamily());
			cv.put("birthdate", person.getBirthdate().getTime());

			id = (int) database.insert("person", null, cv);
		} catch (Exception ex) {
			// TODO
		} finally {
			database.close();
		}

		return id;
	}

	class MySQLiteOpenHelper extends SQLiteOpenHelper {

		public MySQLiteOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String query = "create table person (id integer primary key, name text, family text, birthdate integer)";
			db.execSQL(query);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}

	}

}
