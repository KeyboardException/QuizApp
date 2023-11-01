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
import androidx.appcompat.content.res.AppCompatResources;

import com.github.keyboardexception.quizapp.Activities.QuizActivity;
import com.github.keyboardexception.quizapp.Activities.UserProfileActivity;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.Session;
import com.github.keyboardexception.quizapp.Objects.User;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class RankingAdapter extends ArrayAdapter<User> {

	protected Context context;

	public RankingAdapter(@NonNull Context context, ArrayList<User> users) {
		super(context, 0, users);
		this.context = context;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View currentView = (convertView == null)
			? LayoutInflater.from(getContext()).inflate(R.layout.rank_card, parent, false)
			: convertView;

		User user = getItem(position);
		assert user != null;

		TextView rank = currentView.findViewById(R.id.rank_card_rank);
		TextView name = currentView.findViewById(R.id.rank_card_name);
		TextView point = currentView.findViewById(R.id.rank_card_point);

		rank.setText("#" + user.rank);
		name.setText(user.name);
		point.setText(user.score + " điểm");

		currentView.setOnClickListener(view -> {
			Intent intent = new Intent(context, UserProfileActivity.class);
			intent.putExtra("user", user.id);
			context.startActivity(intent);
		});

		return currentView;
	}
}
