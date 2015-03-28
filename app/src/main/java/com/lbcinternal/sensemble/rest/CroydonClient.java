package com.lbcinternal.sensemble.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;

public class CroydonClient {

    private static final String BASE_URL =
            "https://lbc-shapecroydon-ci-dev.azurewebsites.net";

    private ApiService mApiService;

    public CroydonClient() {
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
