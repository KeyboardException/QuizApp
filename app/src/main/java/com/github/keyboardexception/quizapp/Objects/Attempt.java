package com.github.keyboardexception.quizapp.Objects;

import androidx.annotation.Nullable;

public class Attempt {
	public int id;

	public User user;

	public QuestionBank bank;

	public int total;
	
	public String status;

	public int correct;

	public int score;

	public int created;

	public String[] answers;
}
