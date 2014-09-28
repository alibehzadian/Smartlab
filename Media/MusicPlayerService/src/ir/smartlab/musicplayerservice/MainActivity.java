package ir.smartlab.musicplayerservice;

import com.example.musicplayerservice.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button play, pause, stop;
	public static final String KEY_ACTION = "KEY_ACTION";
	public static final int ACTION_PLAY = 1;
	public static final int ACTION_PAUSE = 2;
	public static final int ACTION_STOP = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		play = (Button) findViewById(R.id.playButton);
		pause = (Button) findViewById(R.id.pauseButton);
		stop = (Button) findViewById(R.id.stopButton);
		
		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		stop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent;
		switch (id) {
		case R.id.playButton:
			intent = new Intent(this, PlayerService.class);
			intent.putExtra(KEY_ACTION, ACTION_PLAY);
			startService(intent);
			break;
		case R.id.pauseButton:
			intent = new Intent(this, PlayerService.class);
			intent.putExtra(KEY_ACTION, ACTION_PAUSE);
			startService(intent);
			break;
		case R.id.stopButton:
			intent = new Intent(this, PlayerService.class);
			intent.putExtra(KEY_ACTION, ACTION_STOP);
			startService(intent);
			break;
		}

	}

}
