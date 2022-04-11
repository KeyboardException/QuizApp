package com.github.keyboardexception.quizapp.Objects;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

import com.github.keyboardexception.quizapp.Main;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Locale;


public class Question {
	@Nullable
	public Integer id;

	public String question;
	public String answer1;
	public String answer2;
	public String answer3;
	public String answer4;
	public int answer;
	public int completed = 0;

	public Question(
		int id,
		String question,
		String answer1,
		String answer2,
		String answer3,
		String answer4,
		int answer,
		int completed
	) {
		this.id = id;
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer = answer;
		this.completed = completed;
	}

	public Question(
		String question,
		String answer1,
		String answer2,
		String answer3,
		String answer4,
		int answer
	) {
		this.id = null;
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer = answer;
		this.completed = 0;
	}

	public void save() {
		ContentValues values = new ContentValues();
		values.put("question", question);
		values.put("answer1", answer1);
		values.put("answer2", answer2);
		values.put("answer3", answer3);
		values.put("answer4", answer4);
		values.put("answer", answer);
		values.put("completed", completed);

		if (id == null) {
			id = Main.DB.insert("questions", values);
		} else {
			values.put("id", id);
			Main.DB.update("questions", values);
		}
	}

	public static ArrayList<Question> gets(int[] ids) {
		SQLiteDatabase db = Main.DB.getReadableDatabase();
		String[] select = new String[ids.length];
		String[] params = new String[ids.length];
		int pos = 0;

		for (int id : ids) {
			select[pos] = "id = ?";
			params[pos++] = String.valueOf(id);
		}

		Cursor cursor = db.rawQuery(
		"SELECT * FROM questions WHERE "
			+ StringUtils.join(select, " OR "), params);

		ArrayList<Question> questions = new ArrayList<>();
		while (cursor.moveToNext()) {
			questions.add(new Question(
				cursor.getInt(0),
				cursor.getString(1),
				cursor.getString(2),
				cursor.getString(3),
				cursor.getString(4),
				cursor.getString(5),
				cursor.getInt(6),
				cursor.getInt(7)
			));
		}

		cursor.close();
		return questions;
	}

	public static Question get(int id) {
		return gets(new int[] { id }).get(0);
	}
}
