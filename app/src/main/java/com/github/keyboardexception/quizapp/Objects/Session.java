package com.github.keyboardexception.quizapp.Objects;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.github.keyboardexception.quizapp.Main;

public class Session {

    public static final String SESSION = "session";
    public static final String TOKEN = "token";

    public Boolean authenticated = false;

    public String session = null;

    @Nullable
    public String method = Session.SESSION;

    @Nullable
    public String token = null;

    public int expire = -1;

    @Nullable
    public User user = null;

    public static void storeToken(String token) {
        SharedPreferences preferences = Main.context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String getStoredToken() {
        SharedPreferences preferences = Main.context.getSharedPreferences("session", Context.MODE_PRIVATE);
        return preferences.getString("token", "");
    }
}
