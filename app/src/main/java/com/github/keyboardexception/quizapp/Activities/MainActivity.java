package com.github.keyboardexception.quizapp.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.github.keyboardexception.quizapp.Adapters.CategoryAdapter;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.Category;
import com.github.keyboardexception.quizapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	protected ListView categoryList;
	protected CategoryAdapter categoryAdapter;
	protected ArrayList<Category> categories;
	protected View results;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		categoryList = findViewById(R.id.mainCategoryList);
		results = findViewById(R.id.main_results);

		results.setOnClickListener(view -> {
			Intent intent = new Intent(this, ResultsActivity.class);
			startActivity(intent);
		});
	}

	@Override
	protected void onStart() {
		super.onStart();

		categories = Category.getAll();
		categoryAdapter = new CategoryAdapter(this, categories);
		categoryList.setAdapter(categoryAdapter);
		categoryAdapter.notifyDataSetChanged();
	}
}