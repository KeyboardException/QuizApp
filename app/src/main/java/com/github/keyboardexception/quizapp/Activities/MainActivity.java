package com.github.keyboardexception.quizapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.github.keyboardexception.quizapp.API.API;
import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.API.ResponseList;
import com.github.keyboardexception.quizapp.API.Responses.AuthResponse;
import com.github.keyboardexception.quizapp.API.Responses.Ranking;
import com.github.keyboardexception.quizapp.Adapters.CategoryAdapter;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.Session;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	protected ListView categoryList;
	protected CategoryAdapter categoryAdapter;
	protected ArrayList<QuestionBank> categories;
	protected View ranking;
	protected View results;
	protected View profile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		categoryList = findViewById(R.id.mainCategoryList);
		ranking = findViewById(R.id.main_ranking);
		results = findViewById(R.id.main_results);
		profile = findViewById(R.id.main_profile);

		ranking.setOnClickListener(view -> {
			Intent intent = new Intent(this, RankingActivity.class);
			startActivity(intent);
		});

		results.setOnClickListener(view -> {
			Intent intent = new Intent(this, ResultsActivity.class);
			startActivity(intent);
		});

		profile.setOnClickListener(view -> {
			Intent intent = new Intent(this, UserProfileActivity.class);
			intent.putExtra("user", Session.currentUser.id);
			startActivity(intent);
		});
	}

	@Override
	protected void onStart() {
		super.onStart();

		new APICallTask().execute();
	}

	protected void initAdapters() {
		categoryAdapter = new CategoryAdapter(this, categories);
		categoryList.setAdapter(categoryAdapter);
		categoryAdapter.notifyDataSetChanged();
	}

	protected class APICallTask extends AsyncTask<Void, Void, ResponseList<QuestionBank>> {
		@Override
		protected ResponseList<QuestionBank> doInBackground(Void... voids) {
			return Main.APIClient.questionBanks();
		}

		@Override
		protected void onPostExecute(ResponseList<QuestionBank> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
					categories = new ArrayList<QuestionBank>(Arrays.asList(response.data));
					initAdapters();
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}
}