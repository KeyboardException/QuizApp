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
import androidx.recyclerview.widget.RecyclerView;

import com.github.keyboardexception.quizapp.Activities.QuizActivity;
import com.github.keyboardexception.quizapp.Activities.ResultActivity;
import com.github.keyboardexception.quizapp.Objects.Category;
import com.github.keyboardexception.quizapp.Objects.Result;
import com.github.keyboardexception.quizapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
	protected View view;
	protected Context context;
	protected ArrayList<Result> results;

	public ResultAdapter(@NonNull Context context, ArrayList<Result> results) {
		this.context = context;
		this.results = results;
	}

	@NonNull
	@Override
	public ResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.result_card, parent, false);
		return new ResultAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ResultAdapter.ViewHolder holder, int position) {
		Result result = results.get(position);

		for (int i = 0; i < holder.dots.length; i++) {
			if (result.answers.get(i).answer == 0) {
				holder.dots[i].setBackgroundResource(R.drawable.question_dot);
				continue;
			}

			holder.dots[i].setBackgroundResource(result.answers.get(i).isCorrect()
				? R.drawable.correct_dot
				: R.drawable.wrong_dot);
		}

		if (result.correct() == result.answers.size())
			view.setBackgroundResource(R.drawable.card_green);
		else if (result.correct() == 0)
			view.setBackgroundResource(R.drawable.card_red);
		else
			view.setBackgroundResource(R.drawable.card_yellow);

		int iconID = result.category.getIcon(context);
		holder.icon.setBackgroundResource(iconID);
		holder.title.setText(result.category.name);

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US);
		Date date = new Date(result.created * 1000L);
		holder.time.setText(format.format(date));

		holder.attachEvent(context, result);
	}

	@Override
	public int getItemCount() {
		return results.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public View view;

		public View icon;
		public TextView title;
		public TextView time;
		public View[] dots;

		public ViewHolder(@NonNull View currentView) {
			super(currentView);

			view = currentView;
			icon = currentView.findViewById(R.id.res_card_icon);
			title = currentView.findViewById(R.id.res_card_name);
			time = currentView.findViewById(R.id.res_card_time);

			dots = new View[] {
				currentView.findViewById(R.id.rcdot_1),
				currentView.findViewById(R.id.rcdot_2),
				currentView.findViewById(R.id.rcdot_3),
				currentView.findViewById(R.id.rcdot_4),
				currentView.findViewById(R.id.rcdot_5)
			};
		}

		public void attachEvent(Context context, Result result) {
			view.setOnClickListener(view -> {
				Intent intent = new Intent(context, ResultActivity.class);
				intent.putExtra("result", result.id);
				context.startActivity(intent);
			});
		}
	}
}
