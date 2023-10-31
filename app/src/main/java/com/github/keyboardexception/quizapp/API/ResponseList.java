package com.github.keyboardexception.quizapp.API;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ResponseList<T> {
    public int code;

    public int status;

    public String description;

    public String caller;

    @Nullable
    public String user = null;

    public T[] data;

    @Nullable
    public String hash = null;

    public float runtime = 0;

    @Nullable
    public Object exception = null;
}
