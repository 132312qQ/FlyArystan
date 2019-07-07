package com.example.fly_arystan.Api;

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

public interface RegistrationApi {

    @GET("register")
    Call<UserRegister> createRegistration(@Query("name") String name,
                                          @Query("surname") String surname,
                                          @Query("birth") String birth,
                                          @Query("national_id") String national_id,
                                          @Query("passport") String passport,
                                          @Query("expire_date") String expire_date,
                                          @Query("mail") String mail,
                                          @Query("mobile") String mobile,
                                          @Query("password1") String password1,
                                          @Query("password2") String password2
    );



}
