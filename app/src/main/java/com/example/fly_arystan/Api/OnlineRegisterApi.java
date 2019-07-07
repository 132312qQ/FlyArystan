package com.example.fly_arystan.Api;

import com.example.fly_arystan.Model.BuyTicket;
import com.example.fly_arystan.Model.OnlineRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OnlineRegisterApi {
    @POST("online-register")
    Call<OnlineRegister> search(@Body OnlineRegister onlineRegister);
}
