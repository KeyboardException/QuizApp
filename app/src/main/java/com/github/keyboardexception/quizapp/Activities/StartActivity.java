package com.github.keyboardexception.quizapp.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.Session;
import com.github.keyboardexception.quizapp.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new APICallTask().execute();
    }

    protected void startIntent(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }

    protected class APICallTask extends AsyncTask<Void, Void, Response<Session>> {
        @Override
        protected Response<Session> doInBackground(Void... voids) {
            return Main.APIClient.verifySession();
        }

        @Override
        protected void onPostExecute(Response<Session> response) {
            boolean sessionValid = false;

            if (response != null && response.data != null) {
                // Handle the API response here
                // You can update UI or perform other tasks based on the data
                if (response.data.authenticated)
                    sessionValid = true;
            }

            if (!sessionValid) {
                startIntent(LoginActivity.class);
            } else {
                startIntent(MainActivity.class);
            }
        }
    }
}
