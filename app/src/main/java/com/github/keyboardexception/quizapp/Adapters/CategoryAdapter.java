package com.github.keyboardexception.quizapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.github.keyboardexception.quizapp.Activities.QuizActivity;
import com.github.keyboardexception.quizapp.Objects.Category;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class CategoryAdapter extends ArrayAdapter<Category> {
	public CategoryAdapter(@NonNull Context context, ArrayList<Category> categories) {
		super(context, 0, categories);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View currentView = (convertView == null)
			? LayoutInflater.from(getContext()).inflate(R.layout.category_card, parent, false)
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

		int iconID = category.getIcon(getContext());
		icon.setBackgroundResource(iconID);
		title.setText(category.name);
		questions.setText(String.format(
			Locale.US,
			"%d/%d câu hỏi",
			category.completed,
			category.questionIDs.size()));

		int prog = Math.round(((float) category.completed / (float) category.questionIDs.size()) * 100);
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
