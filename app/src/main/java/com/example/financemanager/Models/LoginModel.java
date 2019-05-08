package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("EmailAddress")
    public String emailAddress;

    @SerializedName("Password")
    public String password;
}
