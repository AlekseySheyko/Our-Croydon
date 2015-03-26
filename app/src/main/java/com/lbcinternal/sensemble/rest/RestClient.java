package com.lbcinternal.sensemble.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;

public class RestClient {

    private static final String BASE_URL = "http://lbcnews.sensemble.com";

    private ApiService mApiService;

    public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint(BASE_URL)
                .build();

        mApiService = restAdapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
