package com.github.keyboardexception.quizapp.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.github.keyboardexception.quizapp.Main;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;

public class Category {
	public static int SET_SIZE = 5;

	@Nullable
	public Integer id;

	public String name;
	public String icon;
	public ArrayList<Integer> questionIDs;
	public ArrayList<Question> questions;
	public int completed = 0;

	public Category(String name, String icon) {
		this.id = null;
		this.name = name;
		this.icon = icon;
		this.questionIDs = new ArrayList<>();
		this.questions = new ArrayList<>();
		this.completed = 0;
	}

	public Category(String name, String icon, ArrayList<Question> questions) {
		this.id = null;
		this.name = name;
		this.icon = icon;
		this.questions = questions;

		questionIDs = new ArrayList<>();
		for (Question question : questions)
			questionIDs.add(question.id);

		this.completed = 0;
	}

	public Category(int id, String name, String icon, int completed) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.questionIDs = new ArrayList<>();
		this.questions = new ArrayList<>();
		this.completed = completed;
	}

	public Category(int id, String name, String icon, ArrayList<Question> questions, int completed) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.questions = questions;

		questionIDs = new ArrayList<>();
		for (Question question : questions)
			questionIDs.add(question.id);
	}

	public Category(int id, String name, String icon, String questions, int completed) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		questionIDs = new ArrayList<>();

		String[] qids = questions.split(",");
		int[] ids = new int[qids.length];
		int pos = 0;

		for (String sid : qids) {
			ids[pos] = Integer.parseInt(sid);
			questionIDs.add(ids[pos]);
			pos++;
		}

		this.questions = Question.gets(ids);
		this.completed = completed;
	}

	public int getIcon(Context context) {
		return context.getResources()
			.getIdentifier(icon, "drawable", context.getPackageName());
	}

	public void add(Question question) {
		questionIDs.add(question.id);
		questions.add(question);
	}

	public ArrayList<Question> makeQuestionSet() {
		ArrayList<Question> questions = new ArrayList<>();

		if (this.questions.size() < SET_SIZE) {
			Log.e("category", "Cannot make question set for "
				+ this.name + "! (not enough questions)");

			return questions;
		}

		this.questions.sort(Comparator.comparingInt((Question q) -> q.completed));
		for (int i = 0; i < SET_SIZE; i++)
			questions.add(this.questions.get(i));

		return questions;
	}

	public void save() {
		ArrayList<String> ids = new ArrayList<>();
		completed = 0;

		for (Question question : questions) {
			ids.add(String.valueOf(question.id));

			if (question.completed > 0)
				completed += 1;
		}

		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("icon", icon);
		values.put("questions", StringUtils.join(ids, ","));
		values.put("completed", completed);

		if (id == null) {
			id = Main.DB.insert("categories", values);
		} else {
			values.put("id", id);
			Main.DB.update("categories", values);
		}
	}

	public static Category get(int id) {
		SQLiteDatabase db = Main.DB.getReadableDatabase();
		Cursor cursor = db.rawQuery(
			"SELECT * FROM categories WHERE id = ? LIMIT 1",
			new String[] { String.valueOf(id) });

		cursor.moveToFirst();
		Category category = new Category(
			cursor.getInt(0),
			cursor.getString(1),
			cursor.getString(2),
			cursor.getString(3),
			cursor.getInt(4));

		cursor.close();
		return category;
	}

	public static Category getMinimal(int id) {
		SQLiteDatabase db = Main.DB.getReadableDatabase();
		Cursor cursor = db.rawQuery(
			"SELECT * FROM categories WHERE id = ? LIMIT 1",
			new String[] { String.valueOf(id) });

		cursor.moveToFirst();
		Category category = new Category(
			cursor.getInt(0),
			cursor.getString(1),
			cursor.getString(2),
			cursor.getInt(4));

		String[] qids = cursor.getString(3).split(",");
		for (String qid : qids)
			category.questionIDs.add(Integer.parseInt(qid));

		cursor.close();
		return category;
	}

	public static ArrayList<Category> getAll() {
		SQLiteDatabase db = Main.DB.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM categories", null);

		ArrayList<Category> categories = new ArrayList<>();
		while (cursor.moveToNext()) {
			Category category = new Category(
				cursor.getInt(0),
				cursor.getString(1),
				cursor.getString(2),
				cursor.getInt(4));

			String[] qids = cursor.getString(3).split(",");
			for (String qid : qids)
				category.questionIDs.add(Integer.parseInt(qid));

			categories.add(category);
		}

		cursor.close();
		return categories;
	}
}
