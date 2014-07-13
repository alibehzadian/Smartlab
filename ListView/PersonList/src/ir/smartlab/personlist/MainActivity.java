package ir.smartlab.personlist;

import ir.smartlab.personlist.R;
import ir.smartlab.personlist.data.DatabaseAdapter;
import ir.smartlab.personlist.data.Person;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	DatabaseAdapter databaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		databaseAdapter = new DatabaseAdapter(this);

//		for (int i = 0; i < 20; i++) {
//			Person p = new Person();
//			p.setName("Name" + i);
//			p.setFamily("Family" + i);
//			p.setBirthdate(new Date());
//			databaseAdapter.insertPerson(p);
//		}
		
		
		
	}
	
	public void listOnClick(View v) {
		startActivity(new Intent(this, PersonListActivity.class));
	}

		
}
