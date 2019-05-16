package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class ExtractOperationsModel {
    @SerializedName("StartDate")
    public Calendar StartDate;

    @SerializedName("EndDate")
    public Calendar EndDate;

    @SerializedName("UserId")
    public int UserId;
}
