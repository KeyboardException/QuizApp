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

	public QuestionBank questionBank;
	public ArrayList<Answer> answers;
	public long created;
}
