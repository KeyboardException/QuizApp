package com.github.keyboardexception.quizapp.API.Responses;

import androidx.annotation.Nullable;

import com.github.keyboardexception.quizapp.Objects.AttemptQuestion;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.User;

public class AttemptState {
	public String status;

	public int total;

	public int completed;

	@Nullable
	public AttemptQuestion[] questions = null;

	public User user;

	public QuestionBank bank;

	public Result result;

	public static class Result {
		public int correct;

		public float score;
	}
}
