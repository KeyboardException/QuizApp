package com.github.keyboardexception.quizapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.Console;

public class Main extends Application {
	public static DB DB;

	@SuppressLint("StaticFieldLeak")
	public static Context context;

	@Override
	public void onCreate() {
		Log.i("main", "Initializing Database");
		DB = new DB(getApplicationContext());

		Log.i("main", "Initializing Application");
		context = getApplicationContext();
		super.onCreate();
	}
}
