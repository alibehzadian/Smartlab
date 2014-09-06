package com.example.fragmentexample;

import com.example.fragmentexample.data.Book;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetailsFragment extends Fragment {
	private static final String ARG_BOOK = "BOOK_POSITION";

	private Book selectedBook;

	private OnBookDetailsFragmentListener mListener;

	public static BookDetailsFragment newInstance(Book selectedBook) {
		BookDetailsFragment fragment = new BookDetailsFragment();
		Bundle args = new Bundle();
		args.putSerializable(ARG_BOOK, selectedBook);
		fragment.setArguments(args);
		return fragment;
	}

	public BookDetailsFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			selectedBook = (Book) getArguments().getSerializable(ARG_BOOK);
		}
	}
	
	TextView bookName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_book_details, container,
				false);
		
		bookName = (TextView) view.findViewById(R.id.bookName);
		
		updateView(selectedBook);
		
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnBookDetailsFragmentListener) activity;
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
	
	public void updateView(Book selectedBook) {
		Toast.makeText(getActivity(), "Updating details view...", Toast.LENGTH_LONG).show();
		if( selectedBook != null) {
			this.selectedBook = selectedBook;
			bookName.setText(selectedBook.getAuthor());
		}
	}

	public interface OnBookDetailsFragmentListener {
	}

}
