package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

public class ApiResultModel<T> {
    @SerializedName("success")
    public boolean success;

    @SerializedName("errorMessage")
    public String errorMessage;

    @SerializedName("result")
    public T result;
}