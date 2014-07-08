package com.example.databasetest;

import java.util.ArrayList;

import com.example.databasetest.data.Book;
import com.example.databasetest.data.DatabaseAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class BookListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_list);
		
		DatabaseAdapter dbAdapter = new DatabaseAdapter(this);
		ArrayList<Book> books = dbAdapter.getBooks();
		if(books != null) {
			Toast.makeText(this, "Count:" + books.size(), Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "No Books.", Toast.LENGTH_LONG).show();
		}
	}



}
