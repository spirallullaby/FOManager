package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("EmailAddress")
    public String EmailAddress;

    @SerializedName("Password")
    public String Password;
}
