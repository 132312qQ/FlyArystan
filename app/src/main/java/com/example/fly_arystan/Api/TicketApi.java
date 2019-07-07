package com.example.fly_arystan.Api;

import com.example.fly_arystan.Model.Ticket;
import com.example.fly_arystan.Model.UserRegister;

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
    @GET("price")
    Call<Ticket> createTicket(@Query("to") String name,
                              @Query("from") String surname
    );
}