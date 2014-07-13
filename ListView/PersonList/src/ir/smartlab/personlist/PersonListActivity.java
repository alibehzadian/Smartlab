package ir.smartlab.personlist;

import ir.smartlab.personlist.R;
import ir.smartlab.personlist.data.DatabaseAdapter;
import ir.smartlab.personlist.data.Person;
import ir.smartlab.personlist.data.PersonListAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PersonListActivity extends Activity {

	ListView personListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);
		
		DatabaseAdapter dbAdapter = new DatabaseAdapter(this);
		ArrayList<Person> data = (ArrayList<Person>) dbAdapter.getPersons();
		PersonListAdapter listAdapter = new PersonListAdapter(this, data);
		personListView = (ListView) findViewById(R.id.personlistView);
		personListView.setAdapter(listAdapter);
		
		personListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "Click on item:" + position + ", id: " + id, Toast.LENGTH_LONG).show();
			}
		});
		
		personListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "Long click on item:" + position + ", id: " + id, Toast.LENGTH_LONG).show();
				return true;
			}
		});
		
		
	}
}
