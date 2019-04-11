package com.example.financemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financemanager.HttpRequest.AuthenticationRequests;
import com.example.financemanager.HttpRequest.PingServiceCall;
import com.example.financemanager.HttpRequest.PingValue;
import com.example.financemanager.Utils.HttpClient;
import com.example.financemanager.Utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView mTextMessage;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    protected void onClickSignUpButton(View v) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onClickLoginButton(View view) {
        try {
            AuthenticationRequests gitHubService = RetrofitClient.getRetrofitInstance().create(AuthenticationRequests.class);
            Call<List<PingValue>> call = gitHubService.pingServer();
            call.enqueue(new Callback<List<PingValue>>() {
                public String firstResult;
                @Override
                public void onResponse(Call<List<PingValue>> call, Response<List<PingValue>> response) {
                    response.body(); // have your all data
                    PingValue value = response.body().get(0);
                    text = value.toString();
                }

                @Override
                public void onFailure(Call<List<PingValue>> call, Throwable t) {
                }
            });
            String test = text;
        }
        catch(Exception ex)
        {
           text = ex.toString();//handle exception
        }

        HttpClient client = new HttpClient("http://192.168.1.5:8080");
        client.getAsyncCall("FinanceOperationsManager_Server/GetFinanceOperations");

        Toast.makeText(LoginActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}
