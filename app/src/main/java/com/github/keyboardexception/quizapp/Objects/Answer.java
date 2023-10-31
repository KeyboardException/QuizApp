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
}
