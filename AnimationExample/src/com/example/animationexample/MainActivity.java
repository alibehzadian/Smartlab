package com.example.animationexample;

import com.example.animationexample.R.anim;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ObjectAnimator anim;
		
//		overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
		
		b= (Button) findViewById(R.id.button1);
		
		anim = ObjectAnimator.ofFloat(b, "x", 0f, 300f);
		anim.setDuration(6000);
		anim.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				alert("onAnimationStart");
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				alert("onAnimationRepeat");				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				alert("onAnimationEnd");				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				alert("onAnimationCancel");				
			}
		});
		anim.start();
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				alert("Button Clicked.");
//				startActivity(new Intent(MainActivity.this, SecondActivity.class));
				anim.cancel();
			}
		});
		
		Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1);
		anim1.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				alert("Animation End.");
			}
		});
//		b.startAnimation(anim1);
		
		

		
	}
	
	public void alert(String message) {
		Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
	}

}
