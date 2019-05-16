package com.example.financemanager.HttpRequest;

import com.example.financemanager.Models.AddFOModel;
import com.example.financemanager.Models.ApiResultModel;

import retrofit2.Call;
import retrofit2.http.POST;

public interface FinanceOperationsRequests {
    @POST("/add")
    Call<ApiResultModel> addFinanceOperation(AddFOModel model);
}
