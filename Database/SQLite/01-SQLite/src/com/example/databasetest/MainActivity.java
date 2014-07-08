package com.example.databasetest;

import java.util.Calendar;
import java.util.Date;

import com.example.databasetest.data.Book;
import com.example.databasetest.data.DatabaseAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void saveBookOnClick(View v) {
		String name = ((EditText) findViewById(R.id.nameEditText)).getText()
				.toString();
		String author = ((EditText) findViewById(R.id.authorEditText))
				.getText().toString();
		String publisher = ((EditText) findViewById(R.id.publisherEditText))
				.getText().toString();
		String isbn = ((EditText) findViewById(R.id.isbnEditText)).getText()
				.toString();
		DatePicker datePicker = (DatePicker) findViewById(R.id.publishDatePicker);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, datePicker.getYear());
		cal.set(Calendar.MONTH, datePicker.getMonth() + 1);
		cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
		Date date = cal.getTime();
		
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setIsbn(isbn);
		book.setPublisher(publisher);
		book.setPublishDate(date);
		
		DatabaseAdapter dbAdapter = new DatabaseAdapter(this);
		long id = dbAdapter.insertBook(book);
		Toast.makeText(this, "New Book saved. Id=" + id, Toast.LENGTH_LONG).show();
	}
	
	public void allBooksOnClick(View v) {
		startActivity(new Intent(this, BookListActivity.class));
	}

}
