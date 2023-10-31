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
	public int attempt;

	public int id;

	public String question;

	public String answer1;

	public String answer2;

	public String answer3;

	public String answer4;
}
