package com.github.keyboardexception.quizapp.Objects;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.github.keyboardexception.quizapp.Main;

import java.time.Instant;
import java.util.ArrayList;

public class Result {
	@Nullable
	public Integer id;

	public Category category;
	public ArrayList<Answer> answers;
	public long created;

	public Result(int id, int category, String data, long created) {
		this.id = id;
		this.category = Category.getMinimal(category);
		this.answers = Answer.processData(data);
		this.created = created;
	}

	public Result(Category category, ArrayList<Answer> answers) {
		this.id = null;
		this.category = category;
		this.answers = answers;
		this.created = 0;
	}

	public void save() {
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("cid", category.id);
		values.put("data", Answer.craftData(answers));

		if (id == null) {
			values.put("created", Instant.now().getEpochSecond());
			id = Main.DB.insert("results", values);
		} else {
			values.put("id", id);
			Main.DB.update("results", values);
		}
	}

	public int correct() {
		int correct = 0;

		for (Answer answer : answers) {
			if (answer.isCorrect())
				correct += 1;
		}

		return correct;
	}

	public static Result get(int id) {
		SQLiteDatabase db = Main.DB.getReadableDatabase();
		Cursor cursor = db.rawQuery(
			"SELECT * FROM results WHERE id = ? LIMIT 1",
			new String[] { String.valueOf(id) });

		cursor.moveToFirst();
		Result result = new Result(
			cursor.getInt(0),
			cursor.getInt(1),
			cursor.getString(2),
			cursor.getLong(3));

		cursor.close();
		return result;
	}

	public static ArrayList<Result> getAll() {
		SQLiteDatabase db = Main.DB.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM results", null);

		ArrayList<Result> results = new ArrayList<>();
		while (cursor.moveToNext()) {
			Result result = new Result(
				cursor.getInt(0),
				cursor.getInt(1),
				cursor.getString(2),
				cursor.getLong(3));

			results.add(result);
		}

		cursor.close();
		return results;
	}
}
