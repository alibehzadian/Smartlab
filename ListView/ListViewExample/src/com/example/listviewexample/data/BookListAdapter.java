package com.example.listviewexample.data;

import java.util.ArrayList;

import com.example.listviewexample.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookListAdapter extends BaseAdapter {

	Context context;
	ArrayList<Book> data;

	public BookListAdapter(Context context, ArrayList<Book> data) {
		this.context = context;
		this.data = data;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_book_list, null);
			
			holder = new ViewHolder();		
			holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
			holder.authorTextView = (TextView) convertView.findViewById(R.id.authorTextView);
			holder.deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
			holder.deleteButton.setOnClickListener(clickListener);			
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.nameTextView.setText(data.get(position).getName());
		holder.authorTextView.setText(data.get(position).getAuthor());
		holder.deleteButton.setTag(position);

		return convertView;
	}
	
	OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int position = (int) v.getTag();
			Toast.makeText(context, "Bottun clicked on item:" + position, Toast.LENGTH_SHORT).show();
		}
	};

	static class ViewHolder {
		TextView nameTextView;
		TextView authorTextView;
		Button deleteButton;
	}

}
