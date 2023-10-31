package com.github.keyboardexception.quizapp.Objects;


public class AttemptQuestion {
	public int id;

	public String status;

	public String question;

	public String answer1;

	public String answer2;

	public String answer3;

	public String answer4;

	public boolean correct;

	public int answered;

	public int answer;

	public String getAnswer(int answer) {
		switch (answer) {
			case 1:
				return answer1;

			case 2:
				return answer2;

			case 3:
				return answer3;

			case 4:
				return answer4;
		}

		return "UNKNOWN";
	}
}
