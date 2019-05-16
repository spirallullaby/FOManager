package com.example.financemanager.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient<T> {
    private static String BASE_URL;
    private static Gson gson = new Gson();

    public HttpClient(String base_url) {
        BASE_URL = base_url;
    }

    public T get(String url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        url = BASE_URL + "/" + url;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            Type userType = new TypeToken<T>(){}.getType();
            String responseJson = response.body().string();
            T result = gson.fromJson(responseJson, userType);
            return result;
        } catch (IOException ex) {
            throw ex;
        }
    }
}
