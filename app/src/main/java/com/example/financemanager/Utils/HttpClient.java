package com.example.financemanager.Utils;

import android.util.Log;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpClient {
    private static final String TAG = "HttpClient";
    private static String BASE_URL;

    public HttpClient(String base_url) {
        BASE_URL = base_url;
    }

    public <T> void getAsyncCall(String url){
        OkHttpClient httpClient = new OkHttpClient();

        url = BASE_URL + "/" + url;

        Request request = new Request.Builder()
                .url(url)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.e(TAG, "error in getting response using async okhttp call");
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (!response.isSuccessful()) {
                    throw new IOException("Error response " + response);
                }

                Log.i(TAG,responseBody.string());
            }
        });
    }
}
