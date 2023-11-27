package com.github.keyboardexception.quizapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.github.keyboardexception.quizapp.API.API;

import java.io.Console;

public class Main extends Application {

	public static API APIClient;

	@SuppressLint("StaticFieldLeak")
	public static Context context;

	@Override
	public void onCreate() {
		Log.i("main", "Initializing Application");
		context = getApplicationContext();
		super.onCreate();

		APIClient = new API("http://192.168.1.8:8080");
	}
}
