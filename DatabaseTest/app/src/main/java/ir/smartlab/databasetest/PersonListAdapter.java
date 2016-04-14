package ir.smartlab.databasetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ir.smartlab.databasetest.model.Person;

/**
 * Created by Ali on 04/12/2015.
 */
public class PersonListAdapter extends BaseAdapter {

    private ArrayList<Person> persons;
    private Context context;

    public PersonListAdapter(ArrayList<Person> persons, Context context) {
        this.persons = persons;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (persons == null) {
            return 0;
        }
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return persons.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_person_list, parent, false);

        TextView personNameTextView = (TextView) view.findViewById(R.id.personNameTextView);
        personNameTextView.setText(persons.get(position).getName());

        TextView personFamilyTextView = (TextView) view.findViewById(R.id.personFamilyTextView);
        personFamilyTextView.setText(persons.get(position).getFamily());

        return view;
    }
}
