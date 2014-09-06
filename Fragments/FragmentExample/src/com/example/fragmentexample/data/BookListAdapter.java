package com.example.fragmentexample.data;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentexample.R;

public class BookListAdapter extends BaseAdapter {

	Context context;
	ArrayList<Book> data;
	DatabaseAdapter dbAdapter;
	
	public BookListAdapter(Context context, ArrayList<Book> data) {
		this.context = context;
		this.data = data;
		dbAdapter = new DatabaseAdapter(context);
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return data.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if( convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_book_list, null);
			
			holder = new ViewHolder();
			
			holder.nameTextView = (TextView) convertView.findViewById(R.id.bookNameTextView);
			holder.authorTextView = (TextView) convertView.findViewById(R.id.authorNameTextView);
			holder.publishDateTextView = (TextView) convertView.findViewById(R.id.publishDateTextView);
//			holder.deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.nameTextView.setText(data.get(position).getName());
		holder.authorTextView.setText(data.get(position).getAuthor());
		holder.publishDateTextView.setText(data.get(position).getPublishDate().toString());
//		holder.deleteButton.setTag(position);
//		holder.deleteButton.setOnClickListener(deleteButtonOnClick);
		
		return convertView;
	}
	
	private View.OnClickListener deleteButtonOnClick = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Integer position = (Integer) v.getTag();
			Long id = data.get(position).getId();
			
			if(dbAdapter.deleteBook(id) > 0) {
				data.remove(position);
				notifyDataSetChanged();
			}
		}
	};
	
	static class ViewHolder {
		TextView nameTextView;
		TextView authorTextView;
		TextView publishDateTextView;
//		Button deleteButton;
	}

}
