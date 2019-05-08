package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

public class AddFOModel {
    @SerializedName("UserId")
    public int UserId;

    @SerializedName("Sum")
    public double Sum;

    @SerializedName("Description")
    public String Description;
}
