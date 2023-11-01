package com.github.keyboardexception.quizapp.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.API.API;
import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.API.ResponseList;
import com.github.keyboardexception.quizapp.Adapters.CategoryAdapter;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.User;
import com.github.keyboardexception.quizapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class UserProfileActivity extends AppCompatActivity {

	protected TextView name;

	protected TextView username;

	protected TextView joined;

	protected TextView correctValue;

	protected TextView scoreValue;

	protected TextView rankValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile);

		name = findViewById(R.id.profile_name);
		username = findViewById(R.id.profile_username);
		joined = findViewById(R.id.profile_joined);
		correctValue = findViewById(R.id.profile_correct_value);
		scoreValue = findViewById(R.id.profile_score_value);
		rankValue = findViewById(R.id.profile_rank_value);

		Intent intent = getIntent();
		int uid = intent.getIntExtra("user", -1);
		new APICallTask().execute(uid);
	}

	protected void showUser(User user) {
		name.setText(user.name);
		username.setText("@" + user.username);

		Date date = new Date(user.created * 1000L);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		joined.setText("Đã tham gia " + df.format(date));

		correctValue.setText(String.valueOf(user.correct));
		scoreValue.setText(String.valueOf(user.score));
		rankValue.setText("#" + user.rank);
	}

	protected class APICallTask extends AsyncTask<Integer, Void, Response<User>> {
		@Override
		protected Response<User> doInBackground(Integer... params) {
			return Main.APIClient.user(params[0]);
		}

		@Override
		protected void onPostExecute(Response<User> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
					showUser(response.data);
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}
}