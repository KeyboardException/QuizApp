package com.github.keyboardexception.quizapp.Activities;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.BaseInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.API.Responses.AttemptState;
import com.github.keyboardexception.quizapp.API.Responses.NewAttempt;
import com.github.keyboardexception.quizapp.API.Responses.UpdateAttempt;
import com.github.keyboardexception.quizapp.Components.DynamicButton;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.Answer;
import com.github.keyboardexception.quizapp.Objects.Question;
import com.github.keyboardexception.quizapp.R;
import com.github.keyboardexception.quizapp.Sounds;
import com.github.keyboardexception.quizapp.Utils.CountDownTimerWithPause;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import kotlinx.coroutines.channels.Send;

public class QuizActivity extends AppCompatActivity {
	public static long TIME = 300000;

	protected List<Question> questions;

	protected TextView timer;
	protected View[] dots;
	protected AnimatedVectorDrawable clock;
	protected FrameLayout overlay;

	protected TextView questionNum;
	protected TextView questionContent;

	protected RadioGroup answers;
	protected RadioButton answer1;
	protected RadioButton answer2;
	protected RadioButton answer3;
	protected RadioButton answer4;

	protected DynamicButton submit;
	protected LinearLayout result;
	protected TextView resultText;
	protected View resultIcon;

	protected NewAttempt attempt;
	protected Question currentQuestion;
	protected int currentQuestionNum;
	protected int selectedAnswer;
	protected boolean answered = false;
	protected BaseInterpolator interpolator;

	protected CountDownTimerWithPause countTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz);

		clock = (AnimatedVectorDrawable) ((View) findViewById(R.id.qclock)).getBackground();
		timer = findViewById(R.id.qtimer);
		dots = new View[] {
			findViewById(R.id.qdot_1),
			findViewById(R.id.qdot_2),
			findViewById(R.id.qdot_3),
			findViewById(R.id.qdot_4),
			findViewById(R.id.qdot_5)
		};

		overlay = findViewById(R.id.quiz_loading_overlay);
		questionNum = findViewById(R.id.quesnum);
		questionContent = findViewById(R.id.quescontent);

		answers = findViewById(R.id.qansgroup);
		answer1 = findViewById(R.id.qans1);
		answer2 = findViewById(R.id.qans2);
		answer3 = findViewById(R.id.qans3);
		answer4 = findViewById(R.id.qans4);

		submit = findViewById(R.id.achkbtn);
		result = findViewById(R.id.respanel);
		resultText = findViewById(R.id.restext);
		resultIcon = findViewById(R.id.resicon);

		Intent intent = getIntent();
		int cid = intent.getIntExtra("category", -1);
		overlay.setVisibility(View.VISIBLE);
		new StartAttemptTask().execute(cid);
	}

	protected float dp2px(float dp) {
		return TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_DIP,
			dp,
			getResources().getDisplayMetrics()
		);
	}

	protected void start() {
		overlay.setVisibility(View.GONE);
		submit.setOnClickListener(this::onSubmit);
		answers.setOnCheckedChangeListener(this::onAnswerChanged);
		currentQuestionNum = -1;
		interpolator = new OvershootInterpolator(1.4f);

		for (View dot: dots)
			dot.setBackgroundResource(R.drawable.dot);

		hideResultCard();

		countTimer = new CountDownTimerWithPause(TIME, 1000L, true) {
			@Override
			public void onTick(long remain) {
				timer.setText(String.format(Locale.US, "%02d", remain / 1000L));
			}

			@Override
			public void onFinish() {
				timer.setText("00");
				onComplete();
			}
		}.create();

		Sounds.startBGM();
		next();
	}

	protected void showResultCard(boolean correct) {
		if (correct) {
			result.setBackgroundResource(R.drawable.correct_panel);
			resultIcon.setBackgroundResource(R.drawable.correct_dot);
			resultText.setText("Chính Xác!");
		} else {
			result.setBackgroundResource(R.drawable.wrong_panel);
			resultIcon.setBackgroundResource(R.drawable.wrong_dot);
			resultText.setText("Không Chính Xác");
		}

		result.setTranslationY(dp2px(200));
		result.setVisibility(View.VISIBLE);

		// Animate the content view to 100% opacity, and clear any animation
		// listener set on the view.
		result.animate()
			.translationY(dp2px(30))
			.setDuration(500)
			.setInterpolator(interpolator)
			.setListener(null);
	}

	protected void hideResultCard() {
		result.setTranslationY(dp2px(200));
		result.setVisibility(View.GONE);
	}

	protected void next() {
		hideResultCard();

		answered = false;
		currentQuestionNum += 1;

		if (currentQuestionNum >= this.questions.size()) {
			onComplete();
			return;
		}

		countTimer.resume();
		currentQuestion = this.questions.get(currentQuestionNum);

		dots[currentQuestionNum].setBackgroundResource(R.drawable.current_dot);
		questionNum.setText("Câu " + (currentQuestionNum + 1));
		questionContent.setText(currentQuestion.question);
		submit.setColor("blue");
		submit.setText("Kiểm Tra");

		answers.clearCheck();
		answer1.setText(currentQuestion.answer1);
		answer2.setText(currentQuestion.answer2);
		answer3.setText(currentQuestion.answer3);
		answer4.setText(currentQuestion.answer4);
		selectedAnswer = 0;

		answer1.setClickable(true);
		answer2.setClickable(true);
		answer3.setClickable(true);
		answer4.setClickable(true);
	}

	protected void onAnswerChanged(RadioGroup group, int checked) {
		// We can't use switch case here because in gradle 8.0 resources ID
		// will not be final, so ID can be changed anytime affecting switch
		// result.
		if (checked == R.id.qans1)
			selectedAnswer = 1;
		else if (checked == R.id.qans2)
			selectedAnswer = 2;
		else if (checked == R.id.qans3)
			selectedAnswer = 3;
		else if (checked == R.id.qans4)
			selectedAnswer = 4;
	}

	protected void onSubmit(View view) {
		if (answered) {
			Sounds.stop();
			next();
			return;
		}

		if (selectedAnswer == 0)
			return;

		new SendAnswerTask().execute(selectedAnswer);
	}

	protected void resultSubmitted(UpdateAttempt info) {
		if (info.correct) {
			Sounds.correct();
			showResultCard(true);
			submit.setColor("green");
			dots[currentQuestionNum].setBackgroundResource(R.drawable.correct_dot);
		} else {
			Sounds.wrong();
			showResultCard(false);
			submit.setColor("red");
			dots[currentQuestionNum].setBackgroundResource(R.drawable.wrong_dot);
		}

		answer1.setClickable(false);
		answer2.setClickable(false);
		answer3.setClickable(false);
		answer4.setClickable(false);

		answered = true;
		countTimer.pause();

		if ((currentQuestionNum + 1) >= questions.size()) {
			submit.setText("Hoàn Thành");
		} else {
			submit.setText("Câu Tiếp");
		}
	}

	protected void onComplete() {
		countTimer.cancel();
		submit.setEnabled(false);

		new CompleteAttemptTask().execute();
	}

	protected void toResultScreen(int attemptId) {
		Intent intent = new Intent(this, ResultActivity.class);
		intent.putExtra("attempt", attemptId);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		countTimer.cancel();
		Sounds.stopBGM();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1) {
			finish();
		}
	}

	protected class StartAttemptTask extends AsyncTask<Integer, Void, Response<NewAttempt>> {
		@Override
		protected Response<NewAttempt> doInBackground(Integer... params) {
			return Main.APIClient.startAttempt(params[0]);
		}

		@Override
		protected void onPostExecute(Response<NewAttempt> response) {
			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
					questions = new ArrayList<Question>(Arrays.asList(response.data.questions));
					attempt = response.data;
					start();
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}

	protected class SendAnswerTask extends AsyncTask<Integer, Void, Response<UpdateAttempt>> {
		@Override
		protected Response<UpdateAttempt> doInBackground(Integer... params) {
			submit.setEnabled(false);
			return Main.APIClient.updateAttempt(attempt.attempt.id, currentQuestion.attempt, params[0]);
		}

		@Override
		protected void onPostExecute(Response<UpdateAttempt> response) {
			submit.setEnabled(true);

			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
					resultSubmitted(response.data);
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}

	protected class CompleteAttemptTask extends AsyncTask<Void, Void, Response<Object>> {
		@Override
		protected Response<Object> doInBackground(Void... params) {
			submit.setEnabled(false);
			return Main.APIClient.completeAttempt(attempt.attempt.id);
		}

		@Override
		protected void onPostExecute(Response<Object> response) {
			submit.setEnabled(true);

			if (response != null) {
				if (response.code != 0) {
					Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
					return;
				}

				if (response.data != null) {
					// Handle the API response here
					// You can update UI or perform other tasks based on the data
					toResultScreen(attempt.attempt.id);
				}
			} else {
				Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_LONG).show();
			}
		}
	}
}
