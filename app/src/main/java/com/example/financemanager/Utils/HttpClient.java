package com.example.financemanager.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient<T> {
    private static String BASE_URL;

    public HttpClient() {
        BASE_URL = Constants.ServerURL;
    }

    public T get(String url, TypeReference typeReference) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        url = BASE_URL + "/" + url;

        ObjectMapper mapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            String responseJson = response.body().string();
            T result = mapper.readValue(responseJson, typeReference);
            return result;
        } catch (IOException ex) {
            throw ex;
        }
    }

    public T post(String url, Object requestBody, TypeReference typeReference) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        url = BASE_URL + "/" + url;

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(requestBody);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            String responseJson = response.body().string();
            T result = mapper.readValue(responseJson, typeReference);
            return result;
        } catch (IOException ex) {
            throw ex;
        }
    }
}
