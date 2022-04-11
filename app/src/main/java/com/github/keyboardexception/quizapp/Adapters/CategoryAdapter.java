package com.github.keyboardexception.quizapp.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.github.keyboardexception.quizapp.Objects.Category;

public class CategoryAdapters extends ArrayAdapter<Category> {

	public CategoryAdapters(@NonNull Context context, int resource) {
		super(context, resource);
	}
}
