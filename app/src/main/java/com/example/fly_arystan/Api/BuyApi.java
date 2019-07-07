package com.example.fly_arystan.Api;

import com.example.fly_arystan.Model.BuyTicket;
import com.example.fly_arystan.Model.Ticket;
import com.example.fly_arystan.Model.UserRegister;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BuyApi {

//    @GET("buy")
//    Call<BuyTicket> buy(@Query("to") String to,
//                        @Query("from") String from,
//                        @Query("name") String name,
//                        @Query("surname") String surname,
//                        @Query("day") String day,
//                        @Query("month") String month,
//                        @Query("year") String year,
//                        @Query("docnumber") String docnumber,
//                        @Query("tel") String tel,
//                        @Query("email") String email,
//                        @Query("carduser") String carduser,
//                        @Query("cardnum") String cardnum,
//                        @Query("cardMonth") String cardMonth,
//                        @Query("cardYear") String cardYear,
//                        @Query("cvv") String cvv
//    );


//    @FormUrlEncoded
//    @POST("posts")
//    Call<Post> createPost(@FieldMap Map<String, String> fields);


    @POST("buy")
    Call<BuyTicket> buy(@Body BuyTicket buyTicket);



}