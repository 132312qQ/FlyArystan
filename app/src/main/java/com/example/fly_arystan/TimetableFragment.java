package com.example.fly_arystan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fly_arystan.Api.ContactasApi;
import com.example.fly_arystan.Api.TicketApi;
import com.example.fly_arystan.Model.BuyTicket;
import com.example.fly_arystan.Model.Contact;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TimetableFragment extends Fragment {
    private ContactasApi contactasApi;
    private Button send;

    private EditText name;
    private EditText mail;
    private EditText ticket_number;
    private EditText message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_timetable, container, false);

        name = view.findViewById(R.id.);
        mail = view.findViewById(R.id.);
        ticket_number = view.findViewById(R.id.);
        message = view.findViewById(R.id.);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:1111/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        contactasApi = retrofit.create(ContactasApi.class);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost(name.getText().toString(),mail.getText().toString(),ticket_number.getText().toString(),message.getText().toString());
            }
        });

        return view;
    }
    
    private void createPost(String namee,String maill,String ticket_numberr,String messagee) {
        Contact con = new Contact(namee,maill,ticket_numberr,messagee);
        Call<Contact> call = contactasApi.contact(con);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {

                if (!response.isSuccessful()) {
                    name.setText("Code: " + response.code());
                    System.out.println("!!!!!!<<><>><<>" + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("?????????????!?!?!? " + "Error" );
            }
        });
    }
}
