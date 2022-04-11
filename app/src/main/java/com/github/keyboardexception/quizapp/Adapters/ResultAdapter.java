package com.github.keyboardexception.quizapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.keyboardexception.quizapp.Activities.ResultActivity;
import com.github.keyboardexception.quizapp.Objects.Category;
import com.github.keyboardexception.quizapp.Objects.Result;
import com.github.keyboardexception.quizapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ResultAdapter extends ArrayAdapter<Result> {
	public ResultAdapter(@NonNull Context context, ArrayList<Result> results) {
		super(context, 0, results);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View currentView = (convertView == null)
			? LayoutInflater.from(getContext()).inflate(R.layout.result_card, parent, false)
			: convertView;

		Result result = getItem(position);

		View icon = currentView.findViewById(R.id.res_card_icon);
		TextView title = currentView.findViewById(R.id.res_card_name);
		TextView time = currentView.findViewById(R.id.res_card_time);

		View[] dots = new View[] {
			currentView.findViewById(R.id.rcdot_1),
			currentView.findViewById(R.id.rcdot_2),
			currentView.findViewById(R.id.rcdot_3),
			currentView.findViewById(R.id.rcdot_4),
			currentView.findViewById(R.id.rcdot_5)
		};

		for (int i = 0; i < dots.length; i++) {
			if (result.answers.get(i).answer == 0) {
				dots[i].setBackgroundResource(R.drawable.question_dot);
				continue;
			}

			dots[i].setBackgroundResource(result.answers.get(i).isCorrect()
				? R.drawable.correct_dot
				: R.drawable.wrong_dot);
		}

		if (result.correct() == result.answers.size())
			currentView.setBackgroundResource(R.drawable.card_green);
		else if (result.correct() == 0)
			currentView.setBackgroundResource(R.drawable.card_red);
		else
			currentView.setBackgroundResource(R.drawable.card_yellow);

		int iconID = result.category.getIcon(getContext());
		icon.setBackgroundResource(iconID);
		title.setText(result.category.name);

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US);
		Date date = new Date(result.created * 1000L);
		time.setText(format.format(date));

		currentView.setOnClickListener(view -> {
			Intent intent = new Intent(getContext(), ResultActivity.class);
			intent.putExtra("result", result.id);
			getContext().startActivity(intent);
		});

		return currentView;
	}
}
