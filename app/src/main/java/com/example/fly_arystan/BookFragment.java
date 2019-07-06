package com.example.fly_arystan;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fly_arystan.Api.TicketApi;
import com.example.fly_arystan.Model.Ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BookFragment extends Fragment{
    List<String> listImages = new ArrayList<>();
    private TicketApi ticketApi;
    private AutoCompleteTextView from;
    AutoCompleteTextView to;
    private Button search;
    private TextView price;
    private TextView timeGO;
    private TextView timeOut;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_book, container, false);

        from = view.findViewById(R.id.txtRoundFrom);
        to = view.findViewById(R.id.txtRoundTo);

        search = view.findViewById(R.id.btnSearch);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
//                .addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request originalRequest = chain.request();
//
//                Request newRequest = originalRequest.newBuilder()
//                        .header("Interceptor-Header", "xyz")
//                        .build();
//
//                return chain.proceed(newRequest);
//            }
//        })
//                .addInterceptor(loggingInterceptor)
//                .build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        ticketApi = retrofit.create(TicketApi.class);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchticket();
                createPost(from.toString(),to.toString());
            }
        });
        return  view;
    }




    private void createPost(String from,String to) {
        //Post post = new Post(23, "New Title", "New Text");
        Map<String, String> fields = new HashMap<>();
        fields.put("to", from);
        fields.put("from", to);

        Call<Ticket> call = ticketApi.createPost(fields);

        call.enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {

                if (!response.isSuccessful()) {
                    price.setText("Code: " + response.code());
                    return;
                }

                Ticket postResponse = response.body();
                String code = "";
                code += "Code: " + response.code() + "\n";
                price.setText(code);

                String pricetext = "";
                pricetext += "price: " + postResponse.getPrice() + "\n";
                price.setText(pricetext);

                String timego = "";
                timego += "Time GO: " + postResponse.getTimeGo() + "\n";
                timeGO.setText(timego);


                String timeout = "";
                timeout += "Time Out: " + postResponse.getTimeOut() + "\n";
                timeOut.setText(timeout);
            }
            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("?????????????!?!?!? " + "Error" );
            }
        });
    }


    private void searchticket(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.search);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        price = dialog.findViewById(R.id.price);
        timeGO = dialog.findViewById(R.id.timeGo);
        timeOut = dialog.findViewById(R.id.timeOut);

        Button yes = dialog.findViewById(R.id.yes_button);
        Button no = dialog.findViewById(R.id.no_button);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
