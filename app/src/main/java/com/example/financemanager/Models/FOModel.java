package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

public class FOModel{

    @SerializedName("Id")
    public int Id;

    @SerializedName("UserId")
    public int UserId;

    @SerializedName("Sum")
    public double Sum;

    @SerializedName("Description")
    public String Description;

    @SerializedName("Date")
    public java.util.Date Date;
}
