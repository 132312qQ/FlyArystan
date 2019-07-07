package com.example.fly_arystan;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fly_arystan.Api.BuyApi;
import com.example.fly_arystan.Api.RegistrationApi;
import com.example.fly_arystan.Model.BuyTicket;
import com.example.fly_arystan.Model.UserRegister;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityTwo extends AppCompatActivity {
    private BuyApi buyApi;
    private Button buyprice;

    private EditText nam;
    private EditText surnam;
    private EditText birt;
    private EditText national;
    private EditText passpor;
    private EditText expire_dat;
    private EditText mai;
    private EditText mobil;
    private EditText passwor1;
    private EditText passwor2;

    private String toid;
    private String fromid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.buy);

        buyprice = findViewById(R.id.create);

        nam = findViewById(R.id.reg_name);
        surnam = findViewById(R.id.reg_surname);
//        birt = findViewById(R.id.birth);
//        national = findViewById(R.id.national_id);
//        passpor = findViewById(R.id.passport);
//        expire_dat = findViewById(R.id.expire_date);
//        mai = findViewById(R.id.mail);
//        mobil = findViewById(R.id.mobile);
//        passwor1 = findViewById(R.id.password1);
//        passwor2 = findViewById(R.id.password2);

        Intent intent = getIntent();
        toid= intent.getStringExtra("to");
        fromid = intent.getStringExtra("from");




        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:100/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        System.out.println("<>" + toid);
        System.out.println("<><>" + fromid);

        buyApi = retrofit.create(BuyApi.class);

        buyprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("<><><><><>" + toid);
                System.out.println("<><><>><><><" + fromid);
                createPost(toid,fromid,nam.getText().toString(),surnam.getText().toString(),"22","11","2019","423423432","7082923171","nursbrat991229@gmail.com","Nursultan","5578342712280566","09","22","011");
            }
        });


    }

    private void createPost(String toid,String fromid,String name,String surname,String day,String month,String year,String docnumber,String tel,String email,String carduser,String cardnum,String cardMonth,String cardYear,String cvv) {
        BuyTicket buyTicket = new BuyTicket(toid,fromid,name,surname,day,month,year,docnumber,tel,email,carduser,cardnum,cardMonth,cardYear,cvv);
        Call<BuyTicket> call = buyApi.buy(buyTicket);
        call.enqueue(new Callback<BuyTicket>() {
            @Override
            public void onResponse(Call<BuyTicket> call, Response<BuyTicket> response) {

                if (!response.isSuccessful()) {
                    nam.setText("Code: " + response.code());
                    System.out.println("!!!!!!<<><>><<>" + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<BuyTicket> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("?????????????!?!?!? " + "Error" );
            }
        });
    }
}
