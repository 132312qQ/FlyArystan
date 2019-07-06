package com.example.fly_arystan.Api;

import com.example.fly_arystan.Model.Ticket;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface TicketApi {
    @GET("categories/")
    Call<List<Ticket>> getticket(
    );


    @GET("products/cat/{id}")
    Call<Ticket> getCategorybyID(
            @Path("id") String id
    );


    @GET("posts")
    Call<List<Ticket>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );


    @FormUrlEncoded
    @POST("price")
    Call<Ticket> createPost(@FieldMap Map<String, String> fields);
}