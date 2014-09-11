package com.example.compassview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CompassView extends ImageView implements SensorListener {

	private int direction = 0;
	private Paint paint;
	private SensorManager sensorManager;

	public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(2);
		paint.setStyle(Style.STROKE);

		sensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		sensorManager
				.registerListener(this, SensorManager.SENSOR_ACCELEROMETER);

		setImageResource(R.drawable.compass);
	}

	public CompassView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public CompassView(Context context) {
		this(context, null, -1);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int height = this.getHeight();
		int width = this.getWidth();

		canvas.rotate(direction, width / 2, height / 2);
		super.onDraw(canvas);
	}

	public interface CompassListener {
		public void onCompassChanged(CompassView compassView, int data);
	}

	CompassListener compassListener;

	public void setOnCompassListener(CompassListener listener) {
		compassListener = listener;
	}

	@Override
	public void onAccuracyChanged(int arg0, int arg1) {
	}

	@Override
	public void onSensorChanged(int sensor, float[] values) {
		if (sensor != SensorManager.SENSOR_ACCELEROMETER)
			return;
		this.direction = (int) values[0];
		invalidate();
		
		if( compassListener != null) {
			compassListener.onCompassChanged(this, direction);
		}
	}

}
