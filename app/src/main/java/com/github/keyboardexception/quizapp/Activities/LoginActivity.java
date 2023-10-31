package com.github.keyboardexception.quizapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.API.Responses.AuthResponse;
import com.github.keyboardexception.quizapp.Components.BlueButton;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.Session;
import com.github.keyboardexception.quizapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText username;

    EditText password;

    BlueButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.log_username);
        password = findViewById(R.id.log_password);
        btnLogin = findViewById(R.id.log_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new APICallTask().execute();
            }
        });
    }

    protected void startIntent(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    protected class APICallTask extends AsyncTask<Void, Void, Response<AuthResponse>> {
        @Override
        protected Response<AuthResponse> doInBackground(Void... voids) {
            btnLogin.setEnabled(false);

            return Main.APIClient.login(
                username.getText().toString(),
                password.getText().toString()
            );
        }

        @Override
        protected void onPostExecute(Response<AuthResponse> response) {
            btnLogin.setEnabled(true);

            if (response != null) {
                if (response.code != 0) {
                    Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.data != null) {
                    // Handle the API response here
                    // You can update UI or perform other tasks based on the data
                    Session.storeToken(response.data.token);
                    startIntent(MainActivity.class);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Unknown error occured while login", Toast.LENGTH_LONG).show();
            }
        }
    }
}

