package com.lbcinternal.sensemble.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.GsonConverter;

public class RestClient {

    private static final String BASE_URL =
            "https://lbc-shapecroydon-ci-dev.azurewebsites.net";

    private Context mContext;
    private ApiService mApiService;

    public RestClient(Context context) {
        mContext = context;
        buildRestClient(null);
    }

    public RestClient(Context context, String dateFormat) {
        mContext = context;
        buildRestClient(dateFormat);
    }

    private void buildRestClient(String dateFormat) {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setEndpoint(BASE_URL);


        if (dateFormat != null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat(dateFormat)
                    .create();

            builder.setConverter(new GsonConverter(gson));
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        final String sessionKey = sp.getString("sessionId", "");

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override public void intercept(RequestFacade request) {
                request.addHeader("Cookie", sessionKey);
            }
        });

        RestAdapter adapter = builder.build();

        mApiService = adapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
