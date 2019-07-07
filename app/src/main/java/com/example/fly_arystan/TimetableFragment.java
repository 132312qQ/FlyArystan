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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TimetableFragment extends Fragment {

    private Button btnBirthDate;
    private Button btnPasportDate;

    private String birthDate = "";
    private String passportdate = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_timetable, container, false);

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
                System.out.println( ((DatePickerFragment) newFragment).getDate());
            }
        });

        btnPasportDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
                System.out.println( ((DatePickerFragment) newFragment).getDate());
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
                    // Notify the selected item text
                    System.out.println("Selected : " + selectedItemText);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        return view;


    }
}
