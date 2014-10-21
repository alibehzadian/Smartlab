package com.example.activitylifecycletest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}

	public void backOnclick(View v) {
		// Correct:
		// finish();
		
		// Wrong
		startActivity(new Intent(this, MainActivity.class));
	}
}
