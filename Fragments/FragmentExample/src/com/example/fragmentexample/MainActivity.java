package com.example.fragmentexample;

import com.example.fragmentexample.BookDetailsFragment.OnBookDetailsFragmentListener;
import com.example.fragmentexample.BookListFragment.OnBookListFragmentListener;
import com.example.fragmentexample.data.Book;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements OnBookListFragmentListener, OnBookDetailsFragmentListener  {

	boolean largeLayout;
	BookListFragment bookListFragment;
	BookDetailsFragment bookDetailsFragment;
	FragmentManager fragmentManager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        
        largeLayout = getResources().getBoolean(R.bool.isLargeLayout);
        fragmentManager = getFragmentManager();
        
        if( !largeLayout) {
        	bookListFragment = BookListFragment.newInstance();
        	fragmentManager.beginTransaction().add(R.id.fragmentContainer, bookListFragment, BookListFragment.class.getName()).commit();
        }
    }


    @Override
	public void onBookSelected(Book selectedBook) {
    	if( largeLayout) {
    		bookDetailsFragment = (BookDetailsFragment) fragmentManager.findFragmentById(R.id.bookDtailsFragment);
    		bookDetailsFragment.updateView(selectedBook);
    	} else {
    		bookDetailsFragment = BookDetailsFragment.newInstance(selectedBook);
    		fragmentManager.beginTransaction().add(R.id.fragmentContainer, bookDetailsFragment, BookDetailsFragment.class.getName()).addToBackStack(null).commit();
    	}
	}
}
