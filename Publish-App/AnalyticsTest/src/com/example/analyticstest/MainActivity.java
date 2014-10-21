package com.example.analyticstest;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
		Tracker t = analytics.newTracker("UA-43986081-2");	
		t.send(new HitBuilders.AppViewBuilder().build());
	}

}
