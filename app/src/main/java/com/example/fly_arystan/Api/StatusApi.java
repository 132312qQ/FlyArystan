package com.example.fly_arystan.Api;


import com.example.fly_arystan.Model.Status;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface StatusApi {
    @GET("status")
    Call<Status> createTicket(@Query("to") String to,
                              @Query("from") String from,
                              @Query("day") String day

    );
}