package com.github.keyboardexception.quizapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.BaseInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.Components.DynamicButton;
import com.github.keyboardexception.quizapp.Objects.Answer;
import com.github.keyboardexception.quizapp.Objects.Category;
import com.github.keyboardexception.quizapp.Objects.Question;
import com.github.keyboardexception.quizapp.Objects.Result;
import com.github.keyboardexception.quizapp.R;
import com.github.keyboardexception.quizapp.Utils.CountDownTimerWithPause;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
	public static long TIME = 30000;

	protected List<Question> questions;

	protected TextView timer;
	protected View[] dots;
	protected AnimatedVectorDrawable clock;

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

	protected Question currentQuestion;
	protected Category currentCategory;
	protected int currentQuestionNum;
	protected int selectedAnswer;
	protected boolean answered = false;
	protected ArrayList<Answer> answerList;
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
		currentCategory = Category.get(cid);
		questions = currentCategory.makeQuestionSet();
		answerList = new ArrayList<>();

		start();
	}

	protected float dp2px(float dp) {
		return TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_DIP,
			dp,
			getResources().getDisplayMetrics()
		);
	}

	protected void start() {
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
			next();
			return;
		}

		if (selectedAnswer == 0)
			return;

		if (selectedAnswer == currentQuestion.answer) {
			showResultCard(true);
			submit.setColor("green");
			currentQuestion.completed += 1;
			dots[currentQuestionNum].setBackgroundResource(R.drawable.correct_dot);
		} else {
			showResultCard(false);
			submit.setColor("red");
			dots[currentQuestionNum].setBackgroundResource(R.drawable.wrong_dot);
		}

		Answer a = new Answer(currentQuestion, selectedAnswer);
		answerList.add(a);

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
		for (Question question : questions) {
			question.save();
		}

		// Fill in unanswered questions
		for (int i = answerList.size(); i < questions.size(); i++)
			answerList.add(new Answer(questions.get(i), 0));

		Result result = new Result(currentCategory, answerList);
		result.save();
		currentCategory.save();

		Intent intent = new Intent(this, ResultActivity.class);
		intent.putExtra("result", result.id);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		countTimer.cancel();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1) {
			finish();
		}
	}
}
