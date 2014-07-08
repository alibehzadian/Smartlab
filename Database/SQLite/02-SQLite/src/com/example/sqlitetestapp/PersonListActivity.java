package com.example.sqlitetestapp;

import java.util.ArrayList;

import com.example.sqlitetestapp.data.DatabaseAdapter;
import com.example.sqlitetestapp.data.PersonListAdapter;
import com.example.sqlitetestapp.data.model.Person;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonListActivity extends Activity {

	private ListView listView;
	TextView emptyView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);
		
		
		DatabaseAdapter dbAdapter = new DatabaseAdapter(this);
		ArrayList<Person> personList = dbAdapter.getAllPersons();
		
		PersonListAdapter listAdapter = new PersonListAdapter(personList, this);
		
		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(listAdapter);
		
		emptyView = (TextView) findViewById(R.id.emptyView);
		listView.setEmptyView(emptyView);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View convertView, int position, long id) {
				Toast.makeText(getApplicationContext(), "You clicked at:" + position + ", id=" + id, Toast.LENGTH_LONG).show();
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View convertView, int position, long id) {
				Toast.makeText(getApplicationContext(), "You long clicked at:" + position + ", id=" + id, Toast.LENGTH_LONG).show();
				return true;
			}
		});
	}



}
