package com.example.financemanager.HttpRequest;

import android.os.AsyncTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import com.example.financemanager.Utils.RetrofitClient;

public class PingServiceCall extends AsyncTask<Call, Void, String>{

    @Override
    protected String doInBackground(Call... params) {
        try {
            AuthenticationRequests service = RetrofitClient.getRetrofitInstance().create(AuthenticationRequests.class);
            String ping = service.pingServer().execute().body().toString();
            return ping;
        }
        catch(Exception ex)
        {
            String text = ex.toString();//handle exception
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
    }
}