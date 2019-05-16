package com.example.financemanager.HttpRequest;

import com.example.financemanager.Models.LoginModel;
import com.example.financemanager.Models.PingValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthenticationRequests {
    @GET("/ping")
    Call<List<PingValue>> pingServer();

    @POST("apy/user/login")
    Call<LoginModel> login();

}

