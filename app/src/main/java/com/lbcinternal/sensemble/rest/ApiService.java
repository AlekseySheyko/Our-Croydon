package com.lbcinternal.sensemble.rest;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ApiService {

    @GET("/api/news/getall") void getNews (
            Callback<Response> callback);

    @GET("/api/offers/getall") void getOffers (
            Callback<Response> callback);

    @GET("/Api/Posts/GetList/10/0") void getIdeas (
            Callback<Response> callback);

    @POST("/Account/login.aspx") void login (
            @Query("apiLogin") String username,
            @Query("apiPassword") String password,
            @Query("rememberMe") boolean remember,
            Callback<Response> callback);

}
