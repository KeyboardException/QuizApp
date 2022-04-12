package com.github.keyboardexception.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
	public static final String	DB_NAME = "quiz.db";
	public static final int		DB_VERSION = 7;

	protected SQLiteDatabase writableDatabase = null;
	protected SQLiteDatabase readableDatabase = null;

	public DB(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		this.writableDatabase = database;

		Log.i("db", "Creating Tables");
		createTable("questions", new String[] {
			"id INTEGER NOT null PRIMARY KEY AUTOINCREMENT",
			"question TEXT NOT null",
			"answer1 TEXT NOT null",
			"answer2 TEXT NOT null",
			"answer3 TEXT NOT null",
			"answer4 TEXT NOT null",
			"answer INTEGER NOT null",
			"completed INTEGER NOT null DEFAULT 0"
		});

		createTable("categories", new String[] {
			"id INTEGER NOT null PRIMARY KEY AUTOINCREMENT",
			"name TEXT NOT null",
			"icon TEXT NOT null",
			"questions TEXT NOT null",
			"completed INTEGER NOT null DEFAULT 0"
		});

		createTable("results", new String[] {
			"id INTEGER NOT null PRIMARY KEY AUTOINCREMENT",
			"cid INTEGER NOT null",
			"data TEXT NOT null",
			"created LONG NOT null"
		});

		Log.i("db", "Deploying Question Store");
		QuestionStore store = new QuestionStore();
		store.deploy();
	}

	public void createTable(String name, String[] columns) {
		SQLiteDatabase db = getWritableDatabase();
		String sql = String.format(
			"CREATE TABLE %s (%s)",
			name,
			StringUtils.join(columns, ", "));

		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int i, int i1) {
		this.writableDatabase = database;
		Log.i("db", String.format("Performing Database Upgrade (%d -> %d)", i, i1));

		database.execSQL("DROP TABLE IF EXISTS questions");
		database.execSQL("DROP TABLE IF EXISTS categories");
		database.execSQL("DROP TABLE IF EXISTS results");
		onCreate(database);
	}

	public int insert(String table, ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		return (int) db.insert(table, null, values);
	}

	public void update(String table, ContentValues values) {
		if (!values.containsKey("id") || values.get("id") == null) {
			Log.e("db", "DB.update(): cannot update without id");
			return;
		}

		int id = (int) values.get("id");
		values.remove("id");

		SQLiteDatabase db = getWritableDatabase();
		db.update(table, values, "id = ?", new String[] { String.valueOf(id) });
	}

	@Override
	public SQLiteDatabase getWritableDatabase() {
		if (writableDatabase != null)
			return writableDatabase;

		writableDatabase = super.getWritableDatabase();
		return writableDatabase;
	}

	@Override
	public SQLiteDatabase getReadableDatabase() {
		if (readableDatabase != null)
			return readableDatabase;

		readableDatabase = super.getReadableDatabase();
		return readableDatabase;
	}
}
