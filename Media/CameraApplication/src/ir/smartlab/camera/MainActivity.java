package ir.smartlab.camera;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.example.cameraapplication.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity implements SurfaceHolder.Callback,
		Camera.PictureCallback {

	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	Camera camera;
	List<Size> supportedSizes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceHolder.addCallback(this);
	}

	public void captureImageOnClick(View v) {
		if (camera != null) {
			camera.takePicture(null, null, null, this);
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera = Camera.open();

			Camera.Parameters parameters = camera.getParameters();

			if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
				parameters.set("orientation", "portrait");
				// For Android 2.2 and above
				camera.setDisplayOrientation(90);
				// Uncomment for Android 2.0 and above
				 parameters.setRotation(90);
			} else {
				// This is an undocumented although widely known feature
				parameters.set("orientation", "landscape");
				// For Android 2.2 and above
				camera.setDisplayOrientation(0);
				// Uncomment for Android 2.0 and above
				 parameters.setRotation(0);
			}

			// supportedSizes = parameters.getSupportedPictureSizes();
			// for( Size size : supportedSizes) {
			// Log.i("camera", "Width:" + size.width + " Height:" +
			// size.height);
			// }
			// parameters.setPictureSize(supportedSizes.get(supportedSizes.size()-1).width,
			// supportedSizes.get(supportedSizes.size()-1).height);

			parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);

			parameters.setSceneMode(Camera.Parameters.SCENE_MODE_ACTION);

			List<String> colorEffects = parameters.getSupportedColorEffects();
			if (colorEffects != null
					&& colorEffects.contains(Camera.Parameters.EFFECT_MONO)) {
				parameters.setColorEffect(Camera.Parameters.EFFECT_MONO);
			}

			camera.setParameters(parameters);
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (Exception e) {
			camera.release();
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		Uri imageFileUri = getContentResolver().insert(
				Media.EXTERNAL_CONTENT_URI, new ContentValues());
		try {
			OutputStream imageFileOS = getContentResolver().openOutputStream(
					imageFileUri);
			imageFileOS.write(data);
			imageFileOS.flush();
			imageFileOS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		camera.startPreview();
	}
}
