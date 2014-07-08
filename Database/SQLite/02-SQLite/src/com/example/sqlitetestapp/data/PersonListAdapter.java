package com.example.sqlitetestapp.data;

import java.util.ArrayList;

import com.example.sqlitetestapp.R;
import com.example.sqlitetestapp.data.model.Person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PersonListAdapter extends BaseAdapter {

	ArrayList<Person> data;
	Context context;
	DatabaseAdapter dbAdapter;
	
	public PersonListAdapter(ArrayList<Person> data, Context context) {
		this.data = data;
		this.context = context;
		dbAdapter = new DatabaseAdapter(context);
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		return data == null ? null : data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return  data == null ? -1 : data.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		
		if( convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_person_list, null);
			
			holder = new Holder();
			
			holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
			holder.familyTextView = (TextView) convertView.findViewById(R.id.familyTextView);
			holder.birthdateTextView = (TextView) convertView.findViewById(R.id.birthdateTextView);
			holder.deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
			holder.deleteButton.setTag(position);
			holder.deleteButton.setOnClickListener(deleteButtonListener);
			
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		
		holder.nameTextView.setText(data.get(position).getName());
		holder.familyTextView.setText(data.get(position).getFamily());
		holder.birthdateTextView.setText(data.get(position).getBirthdate().toString());
		
		return convertView;
	}

	static class Holder {
		TextView nameTextView;
		TextView familyTextView;
		TextView birthdateTextView;
		Button deleteButton;
	}
	
	OnClickListener deleteButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int position = (Integer) v.getTag();
			dbAdapter.deletePerson(data.get(position).getId());
			
			data.remove(position);
			notifyDataSetChanged();
		}
	};
	
}
