package com.github.keyboardexception.quizapp.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.keyboardexception.quizapp.API.Response;
import com.github.keyboardexception.quizapp.API.Responses.AuthResponse;
import com.github.keyboardexception.quizapp.Components.BlueButton;
import com.github.keyboardexception.quizapp.Components.GreenButton;
import com.github.keyboardexception.quizapp.Main;
import com.github.keyboardexception.quizapp.Objects.Session;
import com.github.keyboardexception.quizapp.R;

public class RegisterActivity extends AppCompatActivity {

    EditText username;

    EditText name;

    EditText email;

    EditText password;

    GreenButton btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = findViewById(R.id.sign_username);
        name = findViewById(R.id.sign_name);
        email = findViewById(R.id.sign_email);
        password = findViewById(R.id.sign_pwd);
        btnSignup = findViewById(R.id.sign_btn);

        btnSignup.setOnClickListener(new View.OnClickListener() {
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
            btnSignup.setEnabled(false);

            return Main.APIClient.signup(
                username.getText().toString(),
                name.getText().toString(),
                email.getText().toString(),
                password.getText().toString()
            );
        }

        @Override
        protected void onPostExecute(Response<AuthResponse> response) {
            btnSignup.setEnabled(true);

            if (response != null) {
                if (response.code != 0) {
                    Toast.makeText(getApplicationContext(), response.description, Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.data != null) {
                    // Handle the API response here
                    // You can update UI or perform other tasks based on the data
                    Session.storeToken(response.data.token);
                    Session.currentUser = response.data.user;
                    startIntent(MainActivity.class);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Unknown error occured while login", Toast.LENGTH_LONG).show();
            }
        }
    }
}

