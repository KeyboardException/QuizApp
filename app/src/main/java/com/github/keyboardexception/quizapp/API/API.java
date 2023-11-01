package com.github.keyboardexception.quizapp.API;

import com.github.keyboardexception.quizapp.API.Responses.AttemptState;
import com.github.keyboardexception.quizapp.API.Responses.AuthResponse;
import com.github.keyboardexception.quizapp.API.Responses.NewAttempt;
import com.github.keyboardexception.quizapp.API.Responses.Ranking;
import com.github.keyboardexception.quizapp.API.Responses.UpdateAttempt;
import com.github.keyboardexception.quizapp.Objects.Attempt;
import com.github.keyboardexception.quizapp.Objects.QuestionBank;
import com.github.keyboardexception.quizapp.Objects.Session;
import com.github.keyboardexception.quizapp.Objects.User;
import com.google.gson.Gson;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    protected String baseUrl;

    protected Service apiService;

    public API(String baseUrl) {
        this.baseUrl = baseUrl;

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(this.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        apiService = retrofit.create(Service.class);
    }

    public Response<Session> verifySession() {
        String token = Session.getStoredToken();

        if (Objects.equals(token, ""))
            token = null;

        try {
            Call<Response<Session>> call = apiService.verifySession(token);
            retrofit2.Response<Response<Session>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                // Handle the error
                return null;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public Response<AuthResponse> login(String username, String password) {
        try {
            Call<Response<AuthResponse>> call = apiService.login(username, password);
            retrofit2.Response<Response<AuthResponse>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                Response<AuthResponse> data = gson.fromJson(response.errorBody().string(), Response.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public Response<AuthResponse> signup(String username, String name, String email, String password) {
        try {
            Call<Response<AuthResponse>> call = apiService.signup(username, name, email, password);
            retrofit2.Response<Response<AuthResponse>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                Response<AuthResponse> data = gson.fromJson(response.errorBody().string(), Response.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public ResponseList<QuestionBank> questionBanks() {
        String token = Session.getStoredToken();

        if (Objects.equals(token, ""))
            token = null;

        try {
            Call<ResponseList<QuestionBank>> call = apiService.questionBanks(token);
            retrofit2.Response<ResponseList<QuestionBank>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                ResponseList<QuestionBank> data = gson.fromJson(response.errorBody().string(), ResponseList.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public Response<NewAttempt> startAttempt(int bankId) {
        String token = Session.getStoredToken();

        if (Objects.equals(token, ""))
            token = null;

        try {
            Call<Response<NewAttempt>> call = apiService.startAttempt("/api/attempt/start/" + bankId, token);
            retrofit2.Response<Response<NewAttempt>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                Response<NewAttempt> data = gson.fromJson(response.errorBody().string(), Response.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public Response<UpdateAttempt> updateAttempt(int attemptId, int qaId, int answer) {
        String token = Session.getStoredToken();

        if (Objects.equals(token, ""))
            token = null;

        try {
            String url = "/api/attempt/" + attemptId + "/answer/" + qaId;
            Call<Response<UpdateAttempt>> call = apiService.updateAttempt(url, token, answer);
            retrofit2.Response<Response<UpdateAttempt>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                Response<UpdateAttempt> data = gson.fromJson(response.errorBody().string(), Response.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public Response<Object> completeAttempt(int attemptId) {
        String token = Session.getStoredToken();

        if (Objects.equals(token, ""))
            token = null;

        try {
            String url = "/api/attempt/complete/" + attemptId;
            Call<Response<Object>> call = apiService.completeAttempt(url, token);
            retrofit2.Response<Response<Object>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                Response<Object> data = gson.fromJson(response.errorBody().string(), Response.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public Response<AttemptState> attempt(int attemptId) {
        String token = Session.getStoredToken();

        if (Objects.equals(token, ""))
            token = null;

        try {
            String url = "/api/attempt/info/" + attemptId;
            Call<Response<AttemptState>> call = apiService.attempt(url, token);
            retrofit2.Response<Response<AttemptState>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                Response<AttemptState> data = gson.fromJson(response.errorBody().string(), Response.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public ResponseList<Attempt> attempts() {
        String token = Session.getStoredToken();

        if (Objects.equals(token, ""))
            token = null;

        try {
            Call<ResponseList<Attempt>> call = apiService.attempts(token);
            retrofit2.Response<ResponseList<Attempt>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                ResponseList<Attempt> data = gson.fromJson(response.errorBody().string(), ResponseList.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public Response<Ranking> ranking() {
        String token = Session.getStoredToken();

        if (Objects.equals(token, ""))
            token = null;

        try {
            Call<Response<Ranking>> call = apiService.ranking(token);
            retrofit2.Response<Response<Ranking>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                Response<Ranking> data = gson.fromJson(response.errorBody().string(), Response.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    public Response<User> user(int id) {
        String token = Session.getStoredToken();

        if (Objects.equals(token, ""))
            token = null;

        try {
            Call<Response<User>> call = apiService.user("/api/user/" + id, token);
            retrofit2.Response<Response<User>> response = call.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                Gson gson = new Gson();
                Response<User> data = gson.fromJson(response.errorBody().string(), Response.class);
                return data;
            }
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }
}

