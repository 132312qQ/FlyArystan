package com.example.fly_arystan;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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


public class BookFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    List<String> listImages = new ArrayList<>();
    private AutoCompleteTextView from;
    AutoCompleteTextView to;

    private Button search;
    private Button btnRoundDepartureDatePicker;

    private TextView price;
    private TextView timeGO;
    private TextView timeOut;
    private TextView departure_date;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_book, container, false);


        search = view.findViewById(R.id.btnSearch);
        btnRoundDepartureDatePicker = view.findViewById(R.id.btnRoundDepartureDatePicker);


        final Spinner spinnerOne = (Spinner) view.findViewById(R.id.spinner_flight_from);

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


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());




    }
}
