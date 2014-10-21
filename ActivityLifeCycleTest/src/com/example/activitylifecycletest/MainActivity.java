package com.example.activitylifecycletest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	public final String TAG = MainActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate:" + System.currentTimeMillis());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "onStart:" + System.currentTimeMillis());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume:" + System.currentTimeMillis());
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause:" + System.currentTimeMillis());
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop:" + System.currentTimeMillis());
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy:" + System.currentTimeMillis());
	}

	public void nextOnClick(View v) {
		startActivity(new Intent(this, SecondActivity.class));
	}
	
	
	
}
