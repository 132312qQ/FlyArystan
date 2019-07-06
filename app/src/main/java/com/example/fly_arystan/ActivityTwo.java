package com.example.fly_arystan;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.SQLOutput;

public class ActivityTwo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.buy);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_status);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.spinner_status, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_status);
        spinner1.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.spinner_status, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter1);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.spinner_status){
            System.out.println("A");
        }
        else if(parent.getId() == R.id.spinner_surname){
            System.out.println("B");
        }

        //System.out.println(parent.getSelectedItemId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
