package ir.smartlab.databasetest.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ali on 08/11/2015.
 */
public class PersonDatabaseAdapter {

    private Context context;
    private SQLiteOpenHelper sqLiteOpenHelper;

    public PersonDatabaseAdapter(Context context) {
        this.context = context;
        sqLiteOpenHelper = new SQLiteOpenHelper(context, "database", null, 1) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                String sql = "create table tbl_persons (id integer primary key, name text, family text)";
                db.execSQL(sql);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        };
    }

    public long savePerson(Person person) {
        String name = person.getName();
        String family = person.getFamily();
        long id = -1;

        SQLiteDatabase database = null;

        try {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("family", family);

            database = sqLiteOpenHelper.getWritableDatabase();
            id = database.insert("tbl_persons", null, values);
        } catch (Exception ex) {
            Log.d("Database", "Exception:" + ex.getMessage());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        return id;
    }

    public Person readPerson(long id) {
        Person person = null;
        String[] columns = new String[]{"id", "name", "family"};
        String selection = "id=?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        SQLiteDatabase database = null;
        try {
            database = sqLiteOpenHelper.getWritableDatabase();
            Cursor cursor = database.query("tbl_persons", columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            if (cursor != null && cursor.moveToFirst()) {
                int idIndex = 0;
                int nameIndex = 1;
                int familyIndex = 2;

                long personId = cursor.getLong(idIndex);
                String personName = cursor.getString(nameIndex);
                String personFamily = cursor.getString(familyIndex);

                person = new Person();
                person.setId(personId);
                person.setName(personName);
                person.setFamily(personFamily);
            }

        } catch (Exception ex) {
            Log.d("Database", "Exception:" + ex.getMessage());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        return person;
    }

    public int updatePerson(Person person) {
        int noOfUpdatedRecords = 0;
        String whereClause = "id=?";
        String[] whereArgs = new String[]{String.valueOf(person.getId())};

        SQLiteDatabase database = null;

        try {
            ContentValues values = new ContentValues();
            values.put("name", person.getName());
            values.put("family", person.getFamily());

            database = sqLiteOpenHelper.getWritableDatabase();
            noOfUpdatedRecords = database.update("tbl_persons", values, whereClause, whereArgs);
        } catch (Exception ex) {
            Log.d("Database", "Exception:" + ex.getMessage());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        return noOfUpdatedRecords;
    }

    public int deletePerson(long id) {
        int noOfDeletedRecords = 0;
        String whereClause = "id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        SQLiteDatabase database = null;

        try {
            database = sqLiteOpenHelper.getWritableDatabase();
            noOfDeletedRecords = database.delete("tbl_persons", whereClause, whereArgs);
        } catch (Exception ex) {
            Log.d("Database", "Exception:" + ex.getMessage());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        return noOfDeletedRecords;
    }

    public ArrayList<Person> readAllPerson(String search) {
        ArrayList<Person> persons = null;
        String[] columns = new String[]{"id", "name", "family"};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        if( search != null && !search.isEmpty()) {
            selection = "name LIKE ? OR family LIKE ?";
            selectionArgs = new String[] {search + "%", search + "%"};
        }

        SQLiteDatabase database = null;
        try {
            database = sqLiteOpenHelper.getWritableDatabase();
            Cursor cursor = database.query("tbl_persons", columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            if (cursor != null && cursor.moveToFirst()) {
                persons = new ArrayList<>();

                int idIndex = 0;
                int nameIndex = 1;
                int familyIndex = 2;

                do {
                    long personId = cursor.getLong(idIndex);
                    String personName = cursor.getString(nameIndex);
                    String personFamily = cursor.getString(familyIndex);

                    Person person = new Person();
                    person.setId(personId);
                    person.setName(personName);
                    person.setFamily(personFamily);

                    persons.add(person);
                } while(cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.d("Database", "Exception:" + ex.getMessage());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        return persons;
    }
}
