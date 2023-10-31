package com.github.keyboardexception.quizapp.API.Responses;

import com.github.keyboardexception.quizapp.Objects.Question;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.User;

public class NewAttempt {
	public AttemptDetail attempt;

	public Question[] questions;

	public static class AttemptDetail {
		public int id;

		public QuestionBank attempt;

		public int created;
	}
}
