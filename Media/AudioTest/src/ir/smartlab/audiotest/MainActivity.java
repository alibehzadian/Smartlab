package ir.smartlab.audiotest;

import java.io.File;
import java.io.IOException;

import com.example.audiotest.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	MediaPlayer mediaPlayer;
	SeekBar seekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mediaPlayer.seekTo(progress);				
			}
		});
		
		mediaPlayer = MediaPlayer.create(this, R.raw.kalimba);
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
			}
		});
		
		mediaPlayer.setOnErrorListener(new OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return false;
			}
		});
		
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				seekBar.setMax(mp.getDuration());
				mp.start();
			}
		});
		
	}
	
	public void playMusic(View v) {
		try {
			mediaPlayer.prepare();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopMusic(View v) {
		if( mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
	}
	
	public void playWithDefaultPlayer(View v) {
		String sdCard = Environment.getExternalStorageDirectory().getAbsolutePath();
		File audioFile = new File(sdCard.concat("/Download/kalimba.mp3"));
		
		Uri uri = Uri.fromFile(audioFile);
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(uri, "audio/mp3");
		startActivity(i);
	}
}
