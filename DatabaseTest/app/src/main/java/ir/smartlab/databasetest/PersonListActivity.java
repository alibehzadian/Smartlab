package ir.smartlab.databasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import ir.smartlab.databasetest.model.Person;
import ir.smartlab.databasetest.model.PersonDatabaseAdapter;

public class PersonListActivity extends AppCompatActivity {

    private ListView personListView;
    private PersonDatabaseAdapter databaseAdapter;
    private ArrayList<Person> persons;
    private EditText filterEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        personListView = (ListView) findViewById(R.id.personListView);
        filterEditText = (EditText) findViewById(R.id.filterEditText);

        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString());
            }
        });

        databaseAdapter = new PersonDatabaseAdapter(this);
        persons = databaseAdapter.readAllPerson(null);

        PersonListAdapter personListAdapter = new PersonListAdapter(persons, this);
        personListView.setAdapter(personListAdapter);
    }

private void search(String s) {
    persons = databaseAdapter.readAllPerson(s);
    PersonListAdapter personListAdapter = new PersonListAdapter(persons, this);
    personListView.setAdapter(personListAdapter);
}
}
