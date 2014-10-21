package com.example.preferencesexample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class MainActivity extends Activity {

	SeekBar seekBar;
	SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		seekBar = (SeekBar) findViewById(R.id.SeekBar);
		preferences = getSharedPreferences("pref", MODE_PRIVATE);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		int progress = preferences.getInt("PROGRESS", 0);
		seekBar.setProgress(progress);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		int progress = seekBar.getProgress();
		preferences.edit().putInt("PROGRESS", progress).commit();
		
		// 2
		Editor editor = preferences.edit();
		editor.putInt("KEY1", 1);
		editor.putBoolean("KEY2", true);
		editor.putLong("KEY3", 45);
		editor.commit();
	}
	
	


}
