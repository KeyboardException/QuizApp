package com.github.keyboardexception.quizapp.API;

import androidx.annotation.Nullable;

public class Response<T> {
    public int code;

    public int status;

    public String description;

    public String caller;

    @Nullable
    public String user = null;

    @Nullable
    public T data = null;

    @Nullable
    public String hash = null;

    public float runtime = 0;

    @Nullable
    public Object exception = null;
}
