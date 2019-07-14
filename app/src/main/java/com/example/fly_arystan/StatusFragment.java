package com.example.fly_arystan;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fly_arystan.Api.RegistrationApi;
import com.example.fly_arystan.Api.StatusApi;
import com.example.fly_arystan.Model.Status;
import com.example.fly_arystan.Model.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StatusFragment extends Fragment {
    private StatusApi statusApi;
    private Button btnSearch;

    private TextView flightNumber;
    private TextView flyFrom;
    private TextView flyTo;
    private TextView message;

    private EditText too;
    private EditText fromm;

    private String nameday;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_status, container, false);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_date);

        btnSearch = view.findViewById(R.id.btnSearch);
        fromm = view.findViewById(R.id.fromm);
        too = view.findViewById(R.id.too);




        // Initializing a String Array
        String[] days = new String[]{
                "Дата отправления",
                "Вчера",
                "Сегодня",
                "Завтра",

        };

        final List<String> daysList = new ArrayList<>(Arrays.asList(days));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>( getContext(),R.layout.spinner_item, daysList){
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
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    nameday = parent.getSelectedItem().toString();
                    // Notify the selected item text
                    System.out.println("Selected : " + selectedItemText);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        statusApi = retrofit.create(StatusApi.class);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost(fromm.getText().toString(),too.getText().toString(),nameday);
                getticket();
            }
        });

        return view;
    }


    private void createPost(String from,String to,String nameday) {
        Call<Status> call = statusApi.createTicket(to,from,nameday);

        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                if (!response.isSuccessful()) {
                    flyTo.setText("Code: " + response.code());
                    return;
                }
                Status postResponse = response.body();
                String flight = "";
                flight += postResponse.getFlight();
                flightNumber.setText(flight);

                String timego1 = "";
                timego1 += postResponse.getTo1();
                flyFrom.setText(timego1);



                String timeout1 = "";
                timeout1 += postResponse.getFrom1();
                flyTo.setText(timeout1);

                String mes = "";
                mes += postResponse.getMessage();
                message.setText(mes);
            }
            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("?????????????!?!?!? " + "Error" );
            }
        });
    }



    private void getticket(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.inside);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        flightNumber = dialog.findViewById(R.id.flightNumber);
        flyFrom = dialog.findViewById(R.id.flyFrom);
        flyTo = dialog.findViewById(R.id.flyTo);
        message = dialog.findViewById(R.id.message);
        dialog.show();
    }
}