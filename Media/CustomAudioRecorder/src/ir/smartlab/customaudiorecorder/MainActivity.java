package ir.smartlab.customaudiorecorder;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.example.customaudiorecorder.R;

import android.app.Activity;
import android.content.ContentValues;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends Activity {

	MediaRecorder recorder;
	File audioFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			recorder = new MediaRecorder();
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

			File path = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/recorder/");
			path.mkdirs();

			audioFile = File.createTempFile(
					"recording_" + new Date().getTime(), ".3gp", path);
			recorder.setOutputFile(audioFile.getAbsolutePath());

			recorder.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startRecordingOnClick(View v) {
		recorder.start();
	}

	public void stopRecordingOnClick(View v) {
		recorder.stop();

		ContentValues contentValues = new ContentValues();
		contentValues.put(MediaStore.MediaColumns.TITLE, "This Isn't Music");
		contentValues.put(MediaStore.Audio.AlbumColumns.ALBUM, "Recordings2");
		contentValues.put(MediaStore.MediaColumns.DATE_ADDED,
				System.currentTimeMillis());
		contentValues.put(MediaStore.Audio.Media.DATA,
				audioFile.getAbsolutePath());
		getContentResolver().insert(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValues);
	}
}
