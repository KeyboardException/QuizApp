package com.github.keyboardexception.quizapp.Objects;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuestionBank {
	public static int SET_SIZE = 5;

	public int id;

	public String name;

	@Nullable
	public String icon;

	public int questions = 0;

	public int completed = 0;

	@SuppressLint("DiscouragedApi")
	public int getIcon(Context context) {
		return context.getResources()
			.getIdentifier(icon, "drawable", context.getPackageName());
	}
}
