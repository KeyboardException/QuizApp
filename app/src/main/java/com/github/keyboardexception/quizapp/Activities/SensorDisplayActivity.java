package com.github.keyboardexception.quizapp.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.API.ResponseList;
import com.github.keyboardexception.quizapp.Adapters.CategoryAdapter;
import com.github.keyboardexception.quizapp.Adapters.SensorDataAdapter;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.SensorData;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SensorDisplayActivity extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	private Sensor lightSensor, proximitySensor, accelerometerSensor, magnetometerSensor;

	private TextView lightValue, proximityValue, accelerometerValue, magnetometerValue, timestampValue;

	private Handler handler;
	private Runnable runnable;

	private float currentLightValue, currentProximityValue, currentAccelerometerValue, currentMagnetometerValue;

	private ListView listViewSensorData;
	private SensorDataAdapter sensorDataAdapter;
	private List<SensorData> sensorDataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor);

		lightValue = findViewById(R.id.lightValue);
		proximityValue = findViewById(R.id.proximityValue);
		accelerometerValue = findViewById(R.id.accelerometerValue);
		magnetometerValue = findViewById(R.id.magnetometerValue);
		timestampValue = findViewById(R.id.timestampValue);

		listViewSensorData = findViewById(R.id.listViewSensorData);
		sensorDataList = new ArrayList<>();
		sensorDataAdapter = new SensorDataAdapter(this, sensorDataList);
		listViewSensorData.setAdapter(sensorDataAdapter);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		lightSensor = getSensor(Sensor.TYPE_LIGHT);
		proximitySensor = getSensor(Sensor.TYPE_PROXIMITY);
		accelerometerSensor = getSensor(Sensor.TYPE_ACCELEROMETER);
		magnetometerSensor = getSensor(Sensor.TYPE_MAGNETIC_FIELD);

		if (lightSensor == null || proximitySensor == null || accelerometerSensor == null || magnetometerSensor == null) {
			// Handle the case where any of the sensors are not available on the device
			// You can display a message or take appropriate action.
			finish();
		}

		handler = new Handler();
		runnable = new Runnable() {
			@Override
			public void run() {
				// Call your function here
				saveValuesToServer();
				updateSensorValuesList();
				handler.postDelayed(this, 3000);
			}
		};

		updateSensorValuesList();
		Button sensorListReload = findViewById(R.id.sensor_item_reload);

		sensorListReload.setOnClickListener(view -> {
			updateSensorValuesList();
		});
	}

	protected void saveValuesToServer() {
		new SensorSaveValueTask().execute("light", String.valueOf(currentLightValue));
		new SensorSaveValueTask().execute("proximity", String.valueOf(currentProximityValue));
		new SensorSaveValueTask().execute("accelerometer", String.valueOf(currentAccelerometerValue));
		new SensorSaveValueTask().execute("magnetometer", String.valueOf(currentMagnetometerValue));
	}

	@Override
	protected void onResume() {
		super.onResume();

		registerSensorListener(lightSensor);
		registerSensorListener(proximitySensor);
		registerSensorListener(accelerometerSensor);
		registerSensorListener(magnetometerSensor);

		updateTimestamp();
		handler.postDelayed(runnable, 3000);
	}

	@Override
	protected void onPause() {
		super.onPause();

		unregisterSensorListener(lightSensor);
		unregisterSensorListener(proximitySensor);
		unregisterSensorListener(accelerometerSensor);
		unregisterSensorListener(magnetometerSensor);

		handler.removeCallbacks(runnable);
	}

	private Sensor getSensor(int sensorType) {
		return sensorManager.getDefaultSensor(sensorType);
	}

	private void registerSensorListener(Sensor sensor) {
		if (sensor != null) {
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	private void unregisterSensorListener(Sensor sensor) {
		if (sensor != null) {
			sensorManager.unregisterListener(this, sensor);
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch (event.sensor.getType()) {
			case Sensor.TYPE_LIGHT:
				updateSensorValue(lightValue, "Light Intensity: " + event.values[0] + " lux");
				currentLightValue = event.values[0];
				break;
			case Sensor.TYPE_PROXIMITY:
				updateSensorValue(proximityValue, "Proximity: " + event.values[0]);
				currentProximityValue = event.values[0];
				break;
			case Sensor.TYPE_ACCELEROMETER:
				updateSensorValue(accelerometerValue, "Accelerometer: X=" + event.values[0] + ", Y=" + event.values[1] + ", Z=" + event.values[2]);
				currentAccelerometerValue = event.values[0];
				break;
			case Sensor.TYPE_MAGNETIC_FIELD:
				updateSensorValue(magnetometerValue, "Magnetometer: X=" + event.values[0] + ", Y=" + event.values[1] + ", Z=" + event.values[2]);
				currentMagnetometerValue = event.values[0];
				break;
		}
		updateTimestamp();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Not needed for this example
	}

	private void updateSensorValue(TextView textView, String value) {
		runOnUiThread(() -> textView.setText(value));
	}

	private void updateTimestamp() {
		runOnUiThread(() -> timestampValue.setText("Timestamp: " + new Date().getTime() / 1000));
	}

	protected void updateSensorValuesList() {
		new SensorGetValuesTask().execute();
	}

	protected void initAdapters() {
		sensorDataAdapter = new SensorDataAdapter(this, sensorDataList);
		listViewSensorData.setAdapter(sensorDataAdapter);
		sensorDataAdapter.notifyDataSetChanged();
	}

	protected class SensorSaveValueTask extends AsyncTask<String, Void, Response<SensorData>> {
		@Override
		protected Response<SensorData> doInBackground(String... args) {
			return Main.APIClient.saveSensorValue(args[0], Float.parseFloat(args[1]));
		}

		@Override
		protected void onPostExecute(Response<SensorData> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}

	protected class SensorGetValuesTask extends AsyncTask<Void, Void, ResponseList<SensorData>> {
		@Override
		protected ResponseList<SensorData> doInBackground(Void... args) {
			return Main.APIClient.getSensorValues();
		}

		@Override
		protected void onPostExecute(ResponseList<SensorData> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
					sensorDataList = new ArrayList<SensorData>(Arrays.asList(response.data));
					initAdapters();
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}
}
