package com.example.financemanager.HttpRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthenticationRequests {
    @GET("FinanceOperationsManager_Server/GetFinanceOperations")
    Call<List<PingValue>> pingServer();
}

