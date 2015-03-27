package com.lbcinternal.sensemble.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class TestClient {

    private static final String BASE_URL = "http://lbcnews.sensemble.com";

    private ApiService mApiService;

    public TestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint(BASE_URL)
                .build();

        mApiService = restAdapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
