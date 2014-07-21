package ir.smartlab.android.broadcast;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView downloadProgressTextView;
	ProgressBar downloadProgressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		downloadProgressTextView = (TextView) findViewById(R.id.downloadProgressTextView);
		downloadProgressBar = (ProgressBar) findViewById(R.id.downloadProgressBar);
	}
	
	public void startDownload(View v) {
		DownloadTask downloadTask = new DownloadTask();
		downloadTask.execute("");
	}

	class DownloadTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected void onPreExecute() {
			downloadProgressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			for (int i = 0; i <= 100; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i);
			}
			return true;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			downloadProgressTextView.setText(values[0] + "% downloaded...");
		}

		@Override
		protected void onPostExecute(Boolean result) {
			downloadProgressBar.setVisibility(View.INVISIBLE);
			if( result) {
				Toast.makeText(getApplicationContext(), "Download Completed.", Toast.LENGTH_LONG)
				.show();
			} else {
				Toast.makeText(getApplicationContext(), "Download Error.", Toast.LENGTH_LONG)
				.show();
			}
		}

	}

}
