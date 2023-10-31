package com.github.keyboardexception.quizapp.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.API.ResponseList;
import com.github.keyboardexception.quizapp.API.Responses.Ranking;
import com.github.keyboardexception.quizapp.Adapters.CategoryAdapter;
import com.github.keyboardexception.quizapp.Adapters.RankingAdapter;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.User;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class RankingActivity extends AppCompatActivity {

	protected TextView rank;
	protected TextView name;
	protected TextView point;

	protected ListView rankingList;
	protected RankingAdapter rankingAdapter;
	protected ArrayList<User> users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rank);

		rank = findViewById(R.id.rank_card_rank);
		name = findViewById(R.id.rank_card_name);
		point = findViewById(R.id.rank_card_point);
		rankingList = findViewById(R.id.ranking_list);
	}

	@Override
	protected void onStart() {
		super.onStart();

		new APICallTask().execute();
	}

	protected void update(Ranking ranking) {
		rank.setText("#" + ranking.me.rank);
		name.setText(ranking.me.name);
		point.setText(ranking.me.score + " điểm");

		users = new ArrayList<User>(Arrays.asList(ranking.ranking));
		rankingAdapter = new RankingAdapter(this, users);
		rankingList.setAdapter(rankingAdapter);
		rankingAdapter.notifyDataSetChanged();
	}

	protected class APICallTask extends AsyncTask<Void, Void, Response<Ranking>> {
		@Override
		protected Response<Ranking> doInBackground(Void... voids) {
			return Main.APIClient.ranking();
		}

		@Override
		protected void onPostExecute(Response<Ranking> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
					update(response.data);
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}
}