package com.lbcinternal.sensemble.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class TestClient {

    private ApiService mApiService;

    public TestClient() {
        String baseUrl = "http://lbcnews.sensemble.com";
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint(baseUrl)
                .build();

        mApiService = restAdapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
