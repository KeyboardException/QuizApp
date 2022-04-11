package com.github.keyboardexception.quizapp;

import android.app.Application;
import android.util.Log;

import java.io.Console;

public class Main extends Application {
	public static DB DB;

	@Override
	public void onCreate() {
		Log.i("main", "Initializing Database");
		DB = new DB(getApplicationContext());

		Log.i("main", "Initializing Application");
		super.onCreate();
	}
}
