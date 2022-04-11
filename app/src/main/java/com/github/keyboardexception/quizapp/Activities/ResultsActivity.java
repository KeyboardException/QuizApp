package com.github.keyboardexception.quizapp.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.Adapters.ResultAdapter;
import com.github.keyboardexception.quizapp.Components.BlueButton;
import com.github.keyboardexception.quizapp.Objects.Result;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

	protected ListView resultList;
	protected BlueButton home;

	protected ArrayList<Result> results;
	protected ResultAdapter resultAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);

		resultList = findViewById(R.id.mainResultList);
		home = findViewById(R.id.res_home);

		home.setOnClickListener(view -> {
			finish();
		});
	}

	@Override
	protected void onStart() {
		super.onStart();

		results = Result.getAll();
		resultAdapter = new ResultAdapter(this, results);
		resultList.setAdapter(resultAdapter);
		resultAdapter.notifyDataSetChanged();
	}
}
