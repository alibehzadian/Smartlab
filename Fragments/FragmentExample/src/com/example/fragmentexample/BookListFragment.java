package com.example.fragmentexample;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fragmentexample.data.Book;
import com.example.fragmentexample.data.BookListAdapter;
import com.example.fragmentexample.data.DatabaseAdapter;

public class BookListFragment extends Fragment {

	private OnBookListFragmentListener mListener;
	private ListView bookListView;

	public static BookListFragment newInstance() {
		BookListFragment fragment = new BookListFragment();
		return fragment;
	}

	public BookListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_book_list, container, false); 
		
		DatabaseAdapter dbAdapter = new DatabaseAdapter(getActivity());
		final ArrayList<Book> data = dbAdapter.getBooks();
		
		BookListAdapter adapter = new BookListAdapter(getActivity(), data);
		
		bookListView = (ListView) view.findViewById(R.id.booksListView);
		
		bookListView.setAdapter(adapter);
		
		bookListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View view, int position, long id) {
				mListener.onBookSelected(data.get(position));
			}
		});
		
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnBookListFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnBookListFragmentListener {
		public void onBookSelected(Book selectedBook);
	}

}
