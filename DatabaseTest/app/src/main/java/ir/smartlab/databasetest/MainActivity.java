package ir.smartlab.databasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ir.smartlab.databasetest.model.Person;
import ir.smartlab.databasetest.model.PersonDatabaseAdapter;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText familyEditText;
    private Button saveButton;
    private Button goToListButton;

    private String name;
    private String family;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        familyEditText = (EditText) findViewById(R.id.familyEditText);
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString();
                family = familyEditText.getText().toString();

                PersonDatabaseAdapter databaseAdapter = new PersonDatabaseAdapter(MainActivity.this);
                Person person = new Person();
                person.setName(name);
                person.setFamily(family);
                databaseAdapter.savePerson(person);
            }
        });

        goToListButton = (Button) findViewById(R.id.goToList);
        goToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonListActivity.class);
                startActivity(intent);
            }
        });
    }

}
