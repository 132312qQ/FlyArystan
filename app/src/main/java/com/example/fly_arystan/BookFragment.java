package com.example.fly_arystan;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fly_arystan.Api.TicketApi;
import com.example.fly_arystan.Model.Ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Button btnRoundDepartureDatePicker;
    private Button search;
    private TextView price;
    private TextView timeGO;
    private TextView timeOut;
    private String toid,fromid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_book, container, false);

        //from = view.findViewById(R.id.txtRoundFrom);
        //to = view.findViewById(R.id.txtRoundTo);

        search = view.findViewById(R.id.btnSearch);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8200/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        ticketApi = retrofit.create(TicketApi.class);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!toid.equals("")&&!fromid.equals("")){
                    searchticket(toid,fromid);
                    System.out.println("<><>< " + toid);
                    System.out.println("<><>< " + fromid);
                    createPost(toid,fromid);
                }
                else {
                    System.out.println("!<><>!<!>" + toid);
                }

            }
        });


        btnRoundDepartureDatePicker = view.findViewById(R.id.btnRoundDepartureDatePicker);
        final Spinner spinnerOne = view.findViewById(R.id.spinner_flight_from);

        final Spinner spinnerTwo = (Spinner) view.findViewById(R.id.spinner_flight_to);

        // Initializing a String Array
        String[] cities_one = new String[]{
                "Откуда",
                "Павлодар",
                "Уральск",
                "Караганда",
                "Алматы",
                "Тараз",
                "Нурсултан",
                "Шымкент",

        };

        String[] cities_two = new String[]{
                "Куда",
                "Павлодар",
                "Уральск",
                "Караганда",
                "Алматы",
                "Тараз",
                "Нурсултан",
                "Шымкент",

        };

        final List<String> citiesList_one = new ArrayList<>(Arrays.asList(cities_one));
        final List<String> citiesList_two = new ArrayList<>(Arrays.asList(cities_two));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapterOne = new ArrayAdapter<String>( getContext(),R.layout.spinner_item, citiesList_one){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {

                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapterOne.setDropDownViewResource(R.layout.spinner_item);
        spinnerOne.setAdapter(spinnerArrayAdapterOne);

        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    fromid = parent.getSelectedItemId() + "";
                    // Notify the selected item text
                    System.out.println("Selected : " + selectedItemText);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayAdapter<String> spinnerArrayAdapterTwo = new ArrayAdapter<String>( getContext(),R.layout.spinner_item, citiesList_two){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapterTwo.setDropDownViewResource(R.layout.spinner_item);
        spinnerTwo.setAdapter(spinnerArrayAdapterTwo);

        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    toid = parent.getSelectedItemId() + "";

                    // Notify the selected item text
                    System.out.println("Selected : " + selectedItemText);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRoundDepartureDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("djfvnfjjj");
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        return  view;
    }




    private void createPost(String from,String to) {
        //Post post = new Post(23, "New Title", "New Text");
//        Map<String, String> fields = new HashMap<>();
//        fields.put("to", to);
//        fields.put("from", from);

        Call<Ticket> call = ticketApi.createTicket(to,from);

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
    private void searchticket(final String toid, final String fromid){
        System.out.println("!>?!>?>!>!?>?>?!?!>!?" + toid);

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
                Intent intent = new Intent(getContext(), ActivityTwo.class);
                intent.putExtra("to",toid);
                intent.putExtra("from",fromid);
                startActivity(intent);
                //buyticket();
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
