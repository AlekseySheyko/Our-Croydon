package com.lbcinternal.sensemble.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;

public class CroydonClient {

    private static final String BASE_URL =
            "https://lbc-shapecroydon-ci-dev.azurewebsites.net";

    private ApiService mApiService;

    public CroydonClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint(BASE_URL);

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        });

        RestAdapter adapter = builder.build();

        mApiService = adapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
