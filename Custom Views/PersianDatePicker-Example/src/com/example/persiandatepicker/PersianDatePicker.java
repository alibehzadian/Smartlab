package com.example.persiandatepicker;

import java.util.Date;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Toast;

public class PersianDatePicker extends LinearLayout {

	private NumberPicker yearNumberPicker, monthNumberPicker, dayNumberPicker;

	public PersianDatePicker(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public PersianDatePicker(Context context) {
		this(context, null, -1);
	}

	public PersianDatePicker(final Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View root = inflater.inflate(R.layout.pdp, this);
		yearNumberPicker = (NumberPicker) root
				.findViewById(R.id.yearNumberPicker);
		monthNumberPicker = (NumberPicker) root
				.findViewById(R.id.monthNumberPicker);
		dayNumberPicker = (NumberPicker) root
				.findViewById(R.id.dayNumberPicker);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PersianDatePicker, 0, 0);
		int year = 1393,month=6,day=19;
		
		year = a.getInteger(R.styleable.PersianDatePicker_year, 1393);
		month = a.getInteger(R.styleable.PersianDatePicker_month, 6);
		day = a.getInteger(R.styleable.PersianDatePicker_day, 19);
		
		a.recycle();

		yearNumberPicker.setMinValue(1380);
		yearNumberPicker.setMaxValue(1400);
		yearNumberPicker.setValue(year);

		monthNumberPicker.setMinValue(1);
		monthNumberPicker.setMaxValue(12);
		monthNumberPicker.setValue(month);

		dayNumberPicker.setMaxValue(31);
		dayNumberPicker.setMinValue(1);
		dayNumberPicker.setValue(day);
		
		yearNumberPicker.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker np, int oldValue, int newValue) {
				Toast.makeText(context, "Year changed:" + oldValue + " -> " + newValue, Toast.LENGTH_LONG).show();
			}
		});
	}

	public Date getSelectedDate() {
		return null;
	}

	public void setSelectedDate(Date date) {

	}

}
