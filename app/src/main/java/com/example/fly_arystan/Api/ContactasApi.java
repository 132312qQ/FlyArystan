package com.example.fly_arystan.Api;

import com.example.fly_arystan.Model.BuyTicket;
import com.example.fly_arystan.Model.Contact;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ContactasApi {
    @POST("contactUs")
    Call<Contact> contact(@Body Contact contact);
}
