package com.github.keyboardexception.quizapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.keyboardexception.quizapp.Objects.SensorData;
import com.github.keyboardexception.quizapp.R;

import java.util.List;

public class SensorDataAdapter extends ArrayAdapter<SensorData> {

	private final Context context;
	private final List<SensorData> sensorDataList;

	public SensorDataAdapter(Context context, List<SensorData> sensorDataList) {
		super(context, R.layout.sensor_item, sensorDataList);
		this.context = context;
		this.sensorDataList = sensorDataList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View rowView = inflater.inflate(R.layout.sensor_item, parent, false);

		TextView sensorID = rowView.findViewById(R.id.sensorID);
		TextView sensorType = rowView.findViewById(R.id.sensorType);
		TextView sensorValue = rowView.findViewById(R.id.sensorValue);
		TextView sensorCreated = rowView.findViewById(R.id.sensorCreated);

		SensorData sensorData = sensorDataList.get(position);

		sensorID.setText("ID: " + sensorData.id);
		sensorType.setText("Type: " + sensorData.type);
		sensorValue.setText("Value: " + sensorData.value);
		sensorCreated.setText("Created: " + sensorData.created);

		return rowView;
	}
}
