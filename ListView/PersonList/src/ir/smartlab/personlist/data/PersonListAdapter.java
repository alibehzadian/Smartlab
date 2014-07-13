package ir.smartlab.personlist.data;

import java.util.ArrayList;

import ir.smartlab.personlist.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PersonListAdapter extends BaseAdapter {

	Context context;
	ArrayList<Person> data;
	
	public PersonListAdapter(Context context, ArrayList<Person> data) {
		this.context = context;
		this.data = data;
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
		return data == null ? -1 : data.get(position).getId();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if( convertView == null ) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_person_list, null);
			
			holder = new ViewHolder();
			
			holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
			holder.familyTextView = (TextView) convertView.findViewById(R.id.familyTextView);
			holder.birthdateTextView = (TextView) convertView.findViewById(R.id.birthdateTextView);
			holder.deleteButton = (Button) convertView.findViewById(R.id.deletePersonButton);
			
			convertView.setTag(holder);			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.nameTextView.setText(data.get(position).getName());
		holder.familyTextView.setText(data.get(position).getFamily());
		holder.birthdateTextView.setText(data.get(position).getBirthdate().toString());
		holder.deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "clicked on delete button: " + position, Toast.LENGTH_LONG).show();
				delete(position);
			}
		});
		
		return convertView;
	}
	
	private void delete(int position) {
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context);
		if( dbAdapter.deletePerson(data.get(position).getId()) > 0 ) {
			data.remove(position);
			notifyDataSetChanged();
		}
	}
	
	static class ViewHolder{
		TextView nameTextView;
		TextView familyTextView;
		TextView birthdateTextView;
		Button deleteButton;
	}

}
