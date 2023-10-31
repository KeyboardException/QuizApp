package com.github.keyboardexception.quizapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.github.keyboardexception.quizapp.Activities.QuizActivity;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class CategoryAdapter extends ArrayAdapter<QuestionBank> {
	public CategoryAdapter(@NonNull Context context, ArrayList<QuestionBank> categories) {
		super(context, 0, categories);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View currentView = (convertView == null)
			? LayoutInflater.from(getContext()).inflate(R.layout.category_card, parent, false)
			: convertView;

		QuestionBank questionBank = getItem(position);
		assert questionBank != null;

		currentView.setOnTouchListener((view, event) -> {
			switch (event.getAction()) {
				case MotionEvent.ACTION_BUTTON_PRESS:
					view.setBackgroundResource(R.drawable.category_card);
					break;

				case MotionEvent.ACTION_UP:
					view.setBackgroundResource(R.drawable.category_card_active);
					view.performClick();
					break;
			}

			return true;
		});

		currentView.setOnClickListener(view -> {
			Intent intent = new Intent(getContext(), QuizActivity.class);
			intent.putExtra("category", questionBank.id);
			getContext().startActivity(intent);
		});

		View icon = currentView.findViewById(R.id.cat_card_icon);
		TextView title = currentView.findViewById(R.id.cat_card_title);
		TextView questions = currentView.findViewById(R.id.cat_card_questions);
		ProgressBar progress = currentView.findViewById(R.id.cat_card_progress);

		int iconID = questionBank.getIcon(getContext());
		icon.setBackgroundResource(iconID);
		title.setText(questionBank.name);
		questions.setText(String.format(
			Locale.US,
			"%d/%d câu hỏi",
			questionBank.completed,
			questionBank.questions));

		int prog = Math.round(((float) questionBank.completed / (float) questionBank.questions) * 100);
		progress.setProgress(prog, true);

		if (prog == 100) {
			progress.setProgressDrawable(
				AppCompatResources.getDrawable(
					getContext(),
					R.drawable.progress_bar_green)
			);
		}

		return currentView;
	}
}
