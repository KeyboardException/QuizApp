package com.github.keyboardexception.quizapp.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.keyboardexception.quizapp.API.ResponseList;
import com.github.keyboardexception.quizapp.Adapters.ResultAdapter;
import com.github.keyboardexception.quizapp.Components.BlueButton;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.Attempt;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.Result;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ResultsActivity extends AppCompatActivity {

	protected RecyclerView resultList;
	protected BlueButton home;

	protected ArrayList<Attempt> results;
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

		new APICallTask().execute();
	}

	protected void initAdapters() {
		resultAdapter = new ResultAdapter(this, results);
		resultList.setAdapter(resultAdapter);
		resultList.setLayoutManager(new LinearLayoutManager(this));
	}

	protected class APICallTask extends AsyncTask<Void, Void, ResponseList<Attempt>> {
		@Override
		protected ResponseList<Attempt> doInBackground(Void... voids) {
			return Main.APIClient.attempts();
		}

		@Override
		protected void onPostExecute(ResponseList<Attempt> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
					results = new ArrayList<>(Arrays.asList(response.data));
					initAdapters();
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}
}
