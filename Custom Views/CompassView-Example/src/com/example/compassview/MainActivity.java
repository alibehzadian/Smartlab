package com.example.compassview;

import com.example.compassview.CompassView.CompassListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	CompassView compassView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		compassView = (CompassView) findViewById(R.id.compassView);
		compassView.setOnCompassListener(new CompassListener() {
			
			@Override
			public void onCompassChanged(CompassView compassView, int data) {
				Toast.makeText(MainActivity.this, "Compass Listener fired", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
}
