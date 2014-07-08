package com.example.sqlitetestapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitetestapp.data.DatabaseAdapter;
import com.example.sqlitetestapp.data.model.Person;

public class MainActivity extends Activity {

	EditText nameEditText;
	EditText familyEditText;
	DatePicker birthdateDatePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		nameEditText = (EditText) findViewById(R.id.nameEditText);
		familyEditText = (EditText) findViewById(R.id.familyEditText);
		birthdateDatePicker = (DatePicker) findViewById(R.id.birthdateDatePicker);
		
		DatabaseAdapter dbAdapter = new DatabaseAdapter(this);
		
		Person p2 = new Person();
		p2.setId(1);
		p2.setName("Mohammad Ali");
		p2.setFamily("Kheiri");
		p2.setBirthdate(new Date());
		dbAdapter.updatePerson(p2);
		
		Person p = dbAdapter.getPersonById(1);
		if( p != null) {
			Toast.makeText(this, "Person=" + p.getName() + " " + p.getFamily(), Toast.LENGTH_LONG).show();
		}
		
		dbAdapter.deletePerson(5);
		
		ArrayList<Person> allPersons = dbAdapter.getAllPersons();
		if( allPersons != null) {
			Toast.makeText(this, "Persons Count=" + allPersons.size(), Toast.LENGTH_LONG).show();
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2012);
		calendar.set(Calendar.MONTH, 2);
		calendar.set(Calendar.DAY_OF_MONTH, 2);
		long base = calendar.getTimeInMillis();
		ArrayList<Person> younerPeople = dbAdapter.getPersonsYoungerThan(base);
		if( younerPeople != null) {
			Toast.makeText(this, "Younger People count=" + younerPeople.size(), Toast.LENGTH_LONG).show();
		}
	}

	public void saveOnclick(View v) {
		Person person = new Person();
		
		String name = nameEditText.getText().toString();
		person.setName(name);
	
		String family = familyEditText.getText().toString();
		person.setFamily(family);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, birthdateDatePicker.getDayOfMonth());
		calendar.set(Calendar.MONTH, birthdateDatePicker.getMonth());
		calendar.set(Calendar.YEAR, birthdateDatePicker.getYear());
		
		Date birthdate = calendar.getTime();
		person.setBirthdate(birthdate);
		
		DatabaseAdapter dbAdapter = new DatabaseAdapter(this);
		dbAdapter.savePerson(person);
	}
	
	public void listOnClick(View v) {
		startActivity(new Intent(this,PersonListActivity.class));
	}
	
}
