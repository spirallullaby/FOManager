package com.example.financemanager.HttpRequest;

import com.google.gson.annotations.SerializedName;

public class PingValue {

    @SerializedName("value")
    private String value;

    @Override
    public String toString() {
        return value;
    }
}