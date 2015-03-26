package com.lbcinternal.sensemble.rest;

import retrofit.client.Response;
import retrofit.http.GET;

public interface ApiService {

    @GET("/api/news/getall") void getNews (
            retrofit.Callback<Response> callback);

}
