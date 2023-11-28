package com.github.keyboardexception.quizapp.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.API.API;
import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.API.ResponseList;
import com.github.keyboardexception.quizapp.Adapters.CategoryAdapter;
import com.github.keyboardexception.quizapp.Adapters.CommentAdapter;
import com.github.keyboardexception.quizapp.Components.BlueButton;
import com.github.keyboardexception.quizapp.Components.DynamicButton;
import com.github.keyboardexception.quizapp.Components.GreenButton;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.Comment;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.Session;
import com.github.keyboardexception.quizapp.Objects.User;
import com.github.keyboardexception.quizapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserProfileActivity extends AppCompatActivity {

	protected TextView name;

	protected TextView username;

	protected TextView joined;

	protected TextView correctValue;

	protected TextView scoreValue;

	protected TextView rankValue;

	protected ListView commentListView;

	protected EditText commentInput;

	protected BlueButton commentSend;

	protected User currentUser;

	private CommentAdapter commentAdapter;
	private List<Comment> commentList;

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

		commentListView = findViewById(R.id.profile_comment_list);
		commentInput = findViewById(R.id.profile_comment_input);
		commentSend = findViewById(R.id.profile_comment_send);

		Intent intent = getIntent();
		int uid = intent.getIntExtra("user", -1);
		new APICallTask().execute(uid);

		commentSend.setOnClickListener(view -> {
			new AddCommentTask().execute(commentInput.getText().toString());
		});
	}

	protected void showUser(User user) {
		currentUser = user;
		name.setText(user.name);
		username.setText("@" + user.username);

		Date date = new Date(user.created * 1000L);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		joined.setText("Đã tham gia " + df.format(date));

		correctValue.setText(String.valueOf(user.correct));
		scoreValue.setText(String.valueOf(user.score));
		rankValue.setText("#" + user.rank);
		new FetchCommentTask().execute();
	}

	protected void initAdapters() {
		commentAdapter = new CommentAdapter(this, commentList);
		commentListView.setAdapter(commentAdapter);
		commentAdapter.notifyDataSetChanged();
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

	protected class AddCommentTask extends AsyncTask<String, Void, Response<Comment>> {
		@Override
		protected Response<Comment> doInBackground(String... params) {
			commentSend.setEnabled(false);
			return Main.APIClient.addComment(currentUser, 5.0f, params[0]);
		}

		@Override
		protected void onPostExecute(Response<Comment> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data

					commentSend.setEnabled(true);
					new FetchCommentTask().execute();
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}

	protected class FetchCommentTask extends AsyncTask<Void, Void, ResponseList<Comment>> {
		@Override
		protected ResponseList<Comment> doInBackground(Void... params) {
			return Main.APIClient.fetchComments(currentUser);
		}

		@Override
		protected void onPostExecute(ResponseList<Comment> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data

					commentList = new ArrayList<Comment>(Arrays.asList(response.data));
					initAdapters();
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}
}