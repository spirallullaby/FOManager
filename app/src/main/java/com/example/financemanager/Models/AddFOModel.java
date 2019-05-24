package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AddFOModel {
    @SerializedName("UserId")
    public int UserId;

    @SerializedName("Type")
    public int Type;

    @SerializedName("Sum")
    public double Sum;

    @SerializedName("Description")
    public String Description;

    @SerializedName("Date")
    public Date Date;
}
