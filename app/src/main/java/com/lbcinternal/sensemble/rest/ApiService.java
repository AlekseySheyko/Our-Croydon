package com.lbcinternal.sensemble.rest;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

public interface ApiService {

    @GET("/api/news/getall") void getNews (
            Callback<Response> callback);

}
