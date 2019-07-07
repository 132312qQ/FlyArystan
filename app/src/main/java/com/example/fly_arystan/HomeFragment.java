package com.example.fly_arystan;

import android.graphics.Color;
import android.os.Bundle;
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

import com.example.fly_arystan.Api.RegistrationApi;
import com.example.fly_arystan.Api.TicketApi;
import com.example.fly_arystan.Model.Ticket;
import com.example.fly_arystan.Model.UserRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    private RegistrationApi registrationApi;
    private Button register;
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
    private Button btnBirthDate;
    private Button btnPasportDate;
    private String countrytext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        nam = view.findViewById(R.id.reg_name);
        surnam = view.findViewById(R.id.reg_surname);
        passpor = view.findViewById(R.id.reg_pasportnumber);
        mai = view.findViewById(R.id.reg_mail);
        mobil = view.findViewById(R.id.reg_number);
        passwor1 = view.findViewById(R.id.password);
        passwor2 = view.findViewById(R.id.checkpassword);
        register = view.findViewById(R.id.create);


        final Spinner spinnerGender = (Spinner) view.findViewById(R.id.spinnerGender);
        final Spinner spinnerLanguage = (Spinner) view.findViewById(R.id.spinnerLanguage);
        final Spinner spinnerCountry = (Spinner) view.findViewById(R.id.spinnerCountry);

        btnBirthDate = view.findViewById(R.id.btnBirthDate);
        btnPasportDate  = view.findViewById(R.id.btnPasportDate);

        btnBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
                //System.out.println( ((DatePickerFragment) newFragment).getDate());
            }
        });

        btnPasportDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
                //System.out.println( ((DatePickerFragment) newFragment).getDate());
            }
        });

        String[] gender = new String[]{
                "Пол",
                "Г-н",
                "Г-жа"

        };

        String[] languages = new String[]{
                "Язык",
                "Английский",
                "Русский",
                "Казахский"

        };

        String[] countries = new String[]{
                "Страна",
                "Казахстан"
        };

        final List<String> genderList = new ArrayList<>(Arrays.asList(gender));
        final List<String> languagesList= new ArrayList<>(Arrays.asList(languages));
        final List<String> countriesList= new ArrayList<>(Arrays.asList(countries));

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

        final ArrayAdapter<String> countriesArrayAdapterOne = new ArrayAdapter<String>( getContext(),R.layout.spinner_item, countriesList){
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
        countriesArrayAdapterOne.setDropDownViewResource(R.layout.spinner_item);
        spinnerCountry.setAdapter(countriesArrayAdapterOne);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    countrytext = parent.getSelectedItemId() + "";
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
                .baseUrl("http://10.0.2.2:2222/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        registrationApi = retrofit.create(RegistrationApi.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost(nam.getText().toString(),surnam.getText().toString(),"07/11/1992",countrytext,passpor.getText().toString(),"02/09/2025",mai.getText().toString(),mobil.getText().toString(),passwor1.getText().toString(),passwor2.getText().toString());
            }
        });
        return  view;

    }
    private void createPost(String name,String surname,String birth,String national_id,String passport,String expire_date,String mail,String mobile,String password1,String password2) {
        Call<UserRegister> call = registrationApi.createRegistration(name,surname,birth,national_id,passport,expire_date,mail,mobile,password1,password2);

        call.enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {

                if (!response.isSuccessful()) {
                    nam.setText("Code: " + response.code());
                    System.out.println("!!!!!!<<><>><<>" + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("?????????????!?!?!? " + "Error" );
            }
        });
    }
}
