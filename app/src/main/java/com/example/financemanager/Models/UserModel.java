package com.example.financemanager.Models;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("Id")
    public int Id;

    @SerializedName("EmailAddress")
    public String EmailAddress;
}
