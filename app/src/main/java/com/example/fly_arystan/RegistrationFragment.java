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
import com.example.fly_arystan.Api.OnlineRegisterApi;
import com.example.fly_arystan.Model.Contact;
import com.example.fly_arystan.Model.OnlineRegister;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegistrationFragment extends Fragment {
    private OnlineRegisterApi onlineRegisterApi;
    private Button btnSearch;

    private EditText name;
    private EditText surname;
    private EditText number;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        btnSearch = view.findViewById(R.id.btnSearch);

        name = view.findViewById(R.id.name);
        surname = view.findViewById(R.id.surname);
        number = view.findViewById(R.id.number);


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:500/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        onlineRegisterApi = retrofit.create(OnlineRegisterApi.class);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost(name.getText().toString(),surname.getText().toString(),number.getText().toString());
            }
        });
        return  view;
    }



    private void createPost(String name,String surname,String number) {
        OnlineRegister con = new OnlineRegister(name,surname,number);
        Call<OnlineRegister> call = onlineRegisterApi.search(con);
        call.enqueue(new Callback<OnlineRegister>() {
            @Override
            public void onResponse(Call<OnlineRegister> call, Response<OnlineRegister> response) {

                if (!response.isSuccessful()) {
                    //name.setText("Code: " + response.code());
                    System.out.println("!!!!!!<<><>><<>" + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<OnlineRegister> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("?????????????!?!?!? " + "Error" );
            }
        });
    }
}
