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

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Service {
    @GET("/api/session")
    @Headers({"Accept: application/json"})
    Call<Response<Session>> verifySession(
        @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("/api/login")
    @Headers({"Accept: application/json"})
    Call<Response<AuthResponse>> login(
        @Field("username") String username,
        @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/register")
    @Headers({"Accept: application/json"})
    Call<Response<AuthResponse>> signup(
        @Field("username") String username,
        @Field("name") String name,
        @Field("email") String email,
        @Field("password") String password
    );

    @GET("/api/banks")
    @Headers({"Accept: application/json"})
    Call<ResponseList<QuestionBank>> questionBanks(
        @Header("Authorization") String token
    );

    @POST
    @Headers({"Accept: application/json"})
    Call<Response<NewAttempt>> startAttempt(
        @Url String url,
        @Header("Authorization") String token
    );

    @POST
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<Response<UpdateAttempt>> updateAttempt(
        @Url String url,
        @Header("Authorization") String token,
        @Field("answer") int answer
    );

    @POST
    @Headers({"Accept: application/json"})
    Call<Response<Object>> completeAttempt(
        @Url String url,
        @Header("Authorization") String token
    );

    @GET
    @Headers({"Accept: application/json"})
    Call<Response<AttemptState>> attempt(
        @Url String url,
        @Header("Authorization") String token
    );

    @GET("/api/attempts")
    @Headers({"Accept: application/json"})
    Call<ResponseList<Attempt>> attempts(
        @Header("Authorization") String token
    );

    @GET("/api/ranking")
    @Headers({"Accept: application/json"})
    Call<Response<Ranking>> ranking(
        @Header("Authorization") String token
    );

    @GET
    @Headers({"Accept: application/json"})
    Call<Response<User>> user(
        @Url String url,
        @Header("Authorization") String token
    );
}
