package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

public class ApiResultModel<T> {
    @SerializedName("Success")
    public boolean Success;

    @SerializedName("ErrorMessage")
    public String ErrorMessage;

    @SerializedName("Result")
    public T Result;
}