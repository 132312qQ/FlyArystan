package com.example.fly_arystan;

import android.graphics.Color;
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

import com.example.fly_arystan.Api.ContactasApi;
import com.example.fly_arystan.Api.TicketApi;
import com.example.fly_arystan.Model.BuyTicket;
import com.example.fly_arystan.Model.Contact;

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

        name = view.findViewById(R.id.name);
        mail = view.findViewById(R.id.surname);
        ticket_number = view.findViewById(R.id.telNumber);
        message = view.findViewById(R.id.message);

        send = view.findViewById(R.id.send);

        final Spinner spinnerGender = (Spinner) view.findViewById(R.id.spinnerContact);
        final Spinner spinnerLanguage = (Spinner) view.findViewById(R.id.spinnerText);


        String[] gender = new String[]{
                "Выбрать",
                "Контакт Центр",
                "Отдел по работе с клиентами",
                "Специальные услуги",
                "Отдел по розыску богажа"

        };

        String[] languages = new String[]{
                "Выбрать",
                "Возрат билета",
                "Перебронирование",
                "Получения копии квитанции",

        };


        final List<String> genderList = new ArrayList<>(Arrays.asList(gender));
        final List<String> languagesList= new ArrayList<>(Arrays.asList(languages));

        final ArrayAdapter<String> genderArrayAdapterOne = new ArrayAdapter<String>( getContext(),R.layout.spinner_item, genderList){
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
        genderArrayAdapterOne.setDropDownViewResource(R.layout.spinner_item);
        spinnerGender.setAdapter(genderArrayAdapterOne);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        final ArrayAdapter<String> languagesArrayAdapterOne = new ArrayAdapter<String>( getContext(),R.layout.spinner_item, languagesList){
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

        languagesArrayAdapterOne.setDropDownViewResource(R.layout.spinner_item);
        spinnerLanguage.setAdapter(languagesArrayAdapterOne);

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
