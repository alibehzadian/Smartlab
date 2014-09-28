package ir.smartlab.audiorecord;

import com.example.audiorecord.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	
	private final int RECORD_REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void recordOnClick(View v) {
		Intent i = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
		startActivityForResult(i, RECORD_REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			Uri recordedFileUri = data.getData();
			MediaPlayer mp = MediaPlayer.create(this, recordedFileUri);
			mp.start();
		}
	}
}
