package com.lbcinternal.sensemble.rest;

import android.util.Base64;

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
        createService(null, null);
    }

    public CroydonClient(String username, String password) {
        createService(username, password);
    }

    private void createService(String username, String password) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint(BASE_URL);

        if (username != null && password != null) {
            final String credentials = username + ":" + password;

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    request.addHeader("Authorization", string);
                    request.addHeader("Accept", "application/json");
                }
            });
        }

        RestAdapter adapter = builder.build();

        mApiService = adapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
