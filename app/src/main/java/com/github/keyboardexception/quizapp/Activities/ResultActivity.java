package com.github.keyboardexception.quizapp.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.API.Responses.AttemptState;
import com.github.keyboardexception.quizapp.Adapters.AnswerAdapter;
import com.github.keyboardexception.quizapp.Components.BlueButton;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.AttemptQuestion;
import com.github.keyboardexception.quizapp.Objects.Result;
import com.github.keyboardexception.quizapp.R;
import com.github.keyboardexception.quizapp.Sounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

	protected LinearLayout header;
	protected View[] dots;
	protected View icon;
	protected TextView title;
	protected ListView answerList;
	protected BlueButton home;

	protected TextView userPoints;
	protected TextView points;
	protected TextView user;
	protected TextView rank;

	protected ArrayList<AttemptQuestion> questions;
	protected AnswerAdapter answerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);

		header = findViewById(R.id.res_header);
		icon = findViewById(R.id.res_caticon);
		title = findViewById(R.id.res_catname);
		answerList = findViewById(R.id.res_catlist);
		home = findViewById(R.id.res_cathome);

		userPoints = findViewById(R.id.result_user_points);
		points = findViewById(R.id.result_points);
		user = findViewById(R.id.result_user);
		rank = findViewById(R.id.result_rank);

		dots = new View[] {
			findViewById(R.id.rqdot_1),
			findViewById(R.id.rqdot_2),
			findViewById(R.id.rqdot_3),
			findViewById(R.id.rqdot_4),
			findViewById(R.id.rqdot_5)
		};

		Sounds.startBGMOut();
		Intent intent = getIntent();
		int rid = intent.getIntExtra("attempt", -1);
		new AttemptResultTask().execute(rid);
	}

	protected void updateResult(AttemptState info) {
		if (info.questions == null) {
			finish();
			return;
		}

		userPoints.setText(String.valueOf(info.user.score));
		points.setText("+" + ((int) info.result.score));
		user.setText(String.valueOf(info.user.name));
		rank.setText("#" + info.user.rank);

		questions = new ArrayList<AttemptQuestion>(Arrays.asList(info.questions));
		answerAdapter = new AnswerAdapter(this, questions);
		answerList.setAdapter(answerAdapter);
		answerAdapter.notifyDataSetChanged();

		for (int i = 0; i < dots.length; i++) {
			if (Objects.equals(questions.get(i).status, "skipped")) {
				dots[i].setBackgroundResource(R.drawable.question_dot);
				continue;
			}

			dots[i].setBackgroundResource(questions.get(i).correct
				? R.drawable.correct_dot
				: R.drawable.wrong_dot);
		}

		int iconID = info.bank.getIcon(this);
		icon.setBackgroundResource(iconID);
		title.setText(info.bank.name);

		int color = 0xFFFFFADB;

		if (info.total == info.result.correct)
			color = 0xFFE8FFDC;
		else if (info.result.correct == 0)
			color = 0xFFFFE8EE;

		header.setBackgroundColor(color);
		getWindow().setStatusBarColor(color);
		Sounds.startBGMOut();

		home.setOnClickListener(view -> {
			finish();
			Sounds.stopBGM();
		});
	}

	@Override
	protected void onDestroy() {
		Sounds.stopBGM();
		super.onDestroy();
	}

	protected class AttemptResultTask extends AsyncTask<Integer, Void, Response<AttemptState>> {
		@Override
		protected Response<AttemptState> doInBackground(Integer... params) {
			return Main.APIClient.attempt(params[0]);
		}

		@Override
		protected void onPostExecute(Response<AttemptState> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
					updateResult(response.data);
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}
}
