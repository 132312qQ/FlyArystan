package com.example.fly_arystan.Api;

import com.example.fly_arystan.Model.Ticket;
import com.example.fly_arystan.Model.UserRegister;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegistrationApi {
    @POST("register")
    Call<UserRegister> createRegistration(@Body UserRegister userRegister);


    //    @FormUrlEncoded
//    @POST("posts")
//    Call<Ticket> createPost(
//            @Field("to") String to,
//            @Field("from") String from
//    );
}
