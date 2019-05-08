package com.example.financemanager.HttpRequest;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import com.example.financemanager.LoginActivity;
import com.example.financemanager.Utils.RetrofitClient;

public class PingServiceCall extends AsyncTask<Call, Void, String>{
    private android.content.Context context = null;

    public PingServiceCall(android.content.Context context) {
        super();
        this.context = context;
    }

    @Override
    public String doInBackground(Call... params) {
        try {
            AuthenticationRequests service = RetrofitClient.getRetrofitInstance().create(AuthenticationRequests.class);
            String ping = service.pingServer().execute().body().get(0).toString();
            return ping;
        }
        catch(Exception ex)
        {
            return ex.toString();//handle exception
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast toast = Toast.makeText(context, result, Toast.LENGTH_LONG);
        toast.show();
    }
}