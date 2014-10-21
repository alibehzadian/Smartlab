package com.example.listviewexample;

import java.util.ArrayList;

import com.example.listviewexample.data.Book;
import com.example.listviewexample.data.BookListAdapter;
import com.example.listviewexample.data.DatabaseAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView bookListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bookListView = (ListView) findViewById(R.id.bookListView);
		DatabaseAdapter database = new DatabaseAdapter(this);
		ArrayList<Book> books = database.getAllBooks();
		BookListAdapter adapter = new BookListAdapter(this, books);
		bookListView.setAdapter(adapter);

		bookListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				alert("Book, id=" + id);
			}
		});
		
		bookListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				alert("Book, id=" + id + " long clicked.");
				return true;
			}
		});

	}

	void alert(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

}
