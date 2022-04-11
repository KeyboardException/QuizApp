package com.github.keyboardexception.quizapp.Objects;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Locale;

public class Answer {
	public Question question;
	public int answer;

	public Answer(Question question, int answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getAnswer(int answer) {
		switch (answer) {
			case 1:
				return question.answer1;

			case 2:
				return question.answer2;

			case 3:
				return question.answer3;

			case 4:
				return question.answer4;
		}

		return "UNKNOWN";
	}

	public boolean isCorrect() {
		return question.answer == answer;
	}

	public static ArrayList<Answer> processData(String data) {
		ArrayList<Answer> answers = new ArrayList<>();

		String[] items = data.split(",");
		for (String item : items) {
			String[] t = item.split(":");
			answers.add(new Answer(
				Question.get(Integer.parseInt(t[0])),
				Integer.parseInt(t[1])
			));
		}

		return answers;
	}

	public static String craftData(ArrayList<Answer> answers) {
		ArrayList<String> parts = new ArrayList<>();

		for (Answer answer : answers)
			parts.add(String.format(Locale.US, "%d:%d", answer.question.id, answer.answer));

		return StringUtils.join(parts, ",");
	}
}
