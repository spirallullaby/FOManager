package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

public class SignUpModel {
    @SerializedName("EmailAddress")
    public String EmailAddress;

    @SerializedName("Password")
    public String Password;

    @SerializedName("RepeatPassword")
    public String RepeatPassword;
}
