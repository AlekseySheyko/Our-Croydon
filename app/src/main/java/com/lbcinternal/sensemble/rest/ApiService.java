package com.lbcinternal.sensemble.rest;

import com.lbcinternal.sensemble.rest.model.User;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiService {

    @GET("/api/news/getall") void getNews(
            Callback<Response> callback);

    @GET("/api/offers/getall") void getOffers(
            Callback<Response> callback);

    @GET("/Api/Posts/GetList/800/0") void getIdeas(
            @Query("order") int order,
            Callback<Response> callback);

    @GET("/Account/login.aspx") void login(
            @Query("apiLogin") String username,
            @Query("apiPassword") String password,
            @Query("rememberMe") boolean remember,
            Callback<User> callback);

}
