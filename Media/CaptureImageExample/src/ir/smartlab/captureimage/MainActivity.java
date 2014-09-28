package ir.smartlab.captureimage;

import java.io.File;

import com.example.captureimageexample.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final int CAPTURE_IMAGE_REQ = 1;
	private final int CAPTURE_IMAGE_FULL_SIZE_REQ = 2;
	private ImageView imageView;
	private String imagePath;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageView = (ImageView) findViewById(R.id.imageView1);
	}
	
	public void captureImageOnClick(View v) {
		Intent captureImageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(captureImageIntent, CAPTURE_IMAGE_REQ);
	}
	
	public void captureFullSizeImage(View v) {
		Intent captureImageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		imagePath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/my_image.jpg");
		Uri fullSizeImageUri = Uri.fromFile(new File(imagePath));
		captureImageIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fullSizeImageUri);
		startActivityForResult(captureImageIntent, CAPTURE_IMAGE_FULL_SIZE_REQ);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
		case CAPTURE_IMAGE_REQ:
			if( resultCode == RESULT_OK) {
				Bitmap bmp = (Bitmap) data.getExtras().get("data");
				imageView.setImageBitmap(bmp);
			} else {
				Toast.makeText(this, "You canceled capturing image.", Toast.LENGTH_LONG).show();
			}
			break;
		case CAPTURE_IMAGE_FULL_SIZE_REQ:
			if( resultCode == RESULT_OK) {
				BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
				bmpFactoryOptions.inSampleSize = 8;
				Bitmap bmp = BitmapFactory.decodeFile(imagePath, bmpFactoryOptions);
				imageView.setImageBitmap(bmp);
			} else {
				Toast.makeText(this, "You canceled capturing image.", Toast.LENGTH_LONG).show();
			}
			break;
		}
	}
}
