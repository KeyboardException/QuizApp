package com.github.keyboardexception.quizapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.keyboardexception.quizapp.Objects.Answer;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;

public class AnswerAdapter extends ArrayAdapter<Answer> {
	public AnswerAdapter(@NonNull Context context, ArrayList<Answer> answers) {
		super(context, 0, answers);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View currentView = (convertView == null)
			? LayoutInflater.from(getContext()).inflate(R.layout.answer_card, parent, false)
			: convertView;

		Answer answer = getItem(position);

		TextView question = currentView.findViewById(R.id.ans_card_question);
		LinearLayout wrong = currentView.findViewById(R.id.ans_card_wrong);
		TextView wrongAnswer = currentView.findViewById(R.id.ans_card_wrong_answer);
		TextView correctAnswer = currentView.findViewById(R.id.ans_card_correct_answer);

		question.setText(answer.question.question);

		if (answer.isCorrect()) {
			currentView.setBackgroundResource(R.drawable.card_green);
			wrong.setVisibility(View.GONE);
			correctAnswer.setText(answer.getAnswer(answer.answer));
		} else {
			currentView.setBackgroundResource(R.drawable.card_red);

			if (answer.answer == 0) {
				wrong.setVisibility(View.GONE);
			} else {
				wrongAnswer.setText(answer.getAnswer(answer.answer));
			}

			correctAnswer.setText(answer.getAnswer(answer.question.answer));
		}

		return currentView;
	}
}
