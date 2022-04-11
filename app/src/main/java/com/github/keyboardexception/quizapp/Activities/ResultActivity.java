package com.github.keyboardexception.quizapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.Adapters.AnswerAdapter;
import com.github.keyboardexception.quizapp.Adapters.CategoryAdapter;
import com.github.keyboardexception.quizapp.Components.BlueButton;
import com.github.keyboardexception.quizapp.Objects.Answer;
import com.github.keyboardexception.quizapp.Objects.Category;
import com.github.keyboardexception.quizapp.Objects.Result;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

	protected LinearLayout header;
	protected View[] dots;
	protected View icon;
	protected TextView title;
	protected ListView answerList;
	protected BlueButton home;

	protected Result currentResult;
	protected ArrayList<Answer> answers;
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

		dots = new View[] {
			findViewById(R.id.rqdot_1),
			findViewById(R.id.rqdot_2),
			findViewById(R.id.rqdot_3),
			findViewById(R.id.rqdot_4),
			findViewById(R.id.rqdot_5)
		};

		Intent intent = getIntent();
		int rid = intent.getIntExtra("result", -1);
		currentResult = Result.get(rid);

		answers = currentResult.answers;
		answerAdapter = new AnswerAdapter(this, answers);
		answerList.setAdapter(answerAdapter);
		answerAdapter.notifyDataSetChanged();

		for (int i = 0; i < dots.length; i++) {
			if (answers.get(i).answer == 0) {
				dots[i].setBackgroundResource(R.drawable.question_dot);
				continue;
			}

			dots[i].setBackgroundResource(answers.get(i).isCorrect()
				? R.drawable.correct_dot
				: R.drawable.wrong_dot);
		}

		int iconID = currentResult.category.getIcon(this);
		icon.setBackgroundResource(iconID);
		title.setText(currentResult.category.name);

		int correct = currentResult.correct();
		int color = 0xFFFFFADB;

		if (correct == currentResult.answers.size())
			color = 0xFFE8FFDC;
		else if (correct == 0)
			color = 0xFFFFE8EE;

		header.setBackgroundColor(color);
		getWindow().setStatusBarColor(color);

		home.setOnClickListener(view -> {
			finish();
		});
	}
}
