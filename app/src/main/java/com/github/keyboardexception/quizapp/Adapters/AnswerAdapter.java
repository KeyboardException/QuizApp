package com.github.keyboardexception.quizapp.Adapters;

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

import com.github.keyboardexception.quizapp.Activities.QuizActivity;
import com.github.keyboardexception.quizapp.Objects.Category;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class ResultAdapter extends ArrayAdapter<Category> {
	public ResultAdapter(@NonNull Context context, ArrayList<Category> categories) {
		super(context, 0, categories);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View currentView = (convertView == null)
			? LayoutInflater.from(getContext()).inflate(R.layout.result_card, parent, false)
			: convertView;

		Category category = getItem(position);

		currentView.setOnTouchListener((view, event) -> {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				view.performClick();
				view.setBackgroundResource(R.drawable.category_card_active);
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				view.setBackgroundResource(R.drawable.category_card);
			}

			return false;
		});

		currentView.setOnClickListener(view -> {
			Intent intent = new Intent(getContext(), QuizActivity.class);
			intent.putExtra("category", category.id);
			getContext().startActivity(intent);
		});

		View icon = currentView.findViewById(R.id.cat_card_icon);
		TextView title = currentView.findViewById(R.id.cat_card_title);
		TextView questions = currentView.findViewById(R.id.cat_card_questions);
		ProgressBar progress = currentView.findViewById(R.id.cat_card_progress);

		int iconID = getContext().getResources().getIdentifier(category.icon, "drawable", getContext().getPackageName());
		icon.setBackgroundResource(iconID);
		title.setText(category.name);
		questions.setText(String.format(Locale.US, "%d câu hỏi", category.questionIDs.size()));
		progress.setProgress((category.completed / category.questionIDs.size()) * 100, true);

		return currentView;
	}
}
