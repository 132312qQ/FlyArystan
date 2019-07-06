package com.example.fly_arystan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class BookFragment extends Fragment  implements View.OnClickListener {
    List<String> listImages = new ArrayList<>();
    private Spinner countriesSpinnerFrom, countriesSpinnerTo ;

    private final int ONE_WAY_DEPARTURE_DATE_PICKER = R.id.btnOneWayDepartureDatePicker;
    private final int ROUND_DEPARTURE_DATE_PICKER = R.id.btnRoundDepartureDatePicker;
    private final int ROUND_RETURN_DATE_PICKER = R.id.btnRoundReturnDatePicker;

    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog2;
    private DatePickerDialog datePickerDialog3;

    private int year;
    private int month;
    private int day;

    private int tempYear;
    private int tempMonth;
    private int tempDay;

    private String oneWayDepartureDate, roundDepartureDate, roundReturnDate;

    private Button btnOneWayDepartureDatePicker;
    private Button btnOneWayClass;
    private Button btnOneWayNumTraveller;


    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_book, container, false);

        btnOneWayDepartureDatePicker = (Button) rootView.findViewById(R.id.btnOneWayDepartureDatePicker);
        btnOneWayDepartureDatePicker.setOnClickListener(this);

        btnOneWayClass = (Button) rootView.findViewById(R.id.btnOneWayClass);
        btnOneWayNumTraveller = (Button) rootView.findViewById(R.id.btnOneWayNumTraveller);

        countriesSpinnerFrom = (Spinner)rootView.findViewById(R.id.spinner_items_from);
        countriesSpinnerTo = (Spinner)rootView.findViewById(R.id.spinner_items_to);
        countriesSpinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                int selected_id = parent.getSelectedItemPosition()+1;

                Toast.makeText(getActivity(), selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        countriesSpinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                int selected_id = parent.getSelectedItemPosition()+1;
                Toast.makeText(getActivity(), selected_id+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        return inflater.inflate(R.layout.fragment_book, container, false);


    }




    public DatePickerDialog datePickerDialog(int datePickerId) {

        year = HelperUtilities.currentYear();
        month = HelperUtilities.currentMonth();
        day = HelperUtilities.currentDay();

        switch (datePickerId) {
            case ONE_WAY_DEPARTURE_DATE_PICKER:
                System.out.println("ffbbb");

                if (datePickerDialog1 == null) {
                    datePickerDialog1 = new DatePickerDialog(getContext(), getOneWayDepartureDatePickerListener(), year, month, day);
                }
                datePickerDialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                return datePickerDialog1;

//            case ROUND_DEPARTURE_DATE_PICKER:
//
//                if (datePickerDialog2 == null) {
//                    datePickerDialog2 = new DatePickerDialog(getContext(), getRoundDepartureDatePickerListener(), year, month, day);
//                }
//                datePickerDialog2.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//                return datePickerDialog2;
//
//            case ROUND_RETURN_DATE_PICKER:
//
//                if (datePickerDialog3 == null) {
//                    datePickerDialog3 = new DatePickerDialog(getContext(), getRoundReturnDatePickerListener(), year, month, day);
//                }
//                datePickerDialog3.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//                return datePickerDialog3;
        }
        return null;
    }

    public DatePickerDialog.OnDateSetListener getOneWayDepartureDatePickerListener() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int startYear, int startMonth, int startDay) {

                //get one way departure date here

                oneWayDepartureDate = startYear + "-" + (startMonth + 1) + "-" + startDay;
                btnOneWayDepartureDatePicker.setText(HelperUtilities.formatDate(startYear, startMonth, startDay));

            }
        };
    }



//    public DatePickerDialog.OnDateSetListener getRoundReturnDatePickerListener() {
//        return new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int startYear, int startMonth, int startDay) {
//
//                String departureDate = tempYear + "-" + (tempMonth + 1) + "-" + tempDay;
//                String returnDate = startYear + "-" + (startMonth + 1) + "-" + startDay;
//
//                if (HelperUtilities.compareDate(departureDate, returnDate)) {
//                    datePickerAlert().show();
//                    isValidRoundDate = false;
//                } else {
//                    isValidRoundDate = true;
//                    //get round trip return date here
//                    roundReturnDate = startYear + "-" + (startMonth + 1) + "-" + startDay;
//                    btnRoundReturnDatePicker.setText(HelperUtilities.formatDate(startYear, startMonth, startDay));
//                }
//            }
//        };
//    }

    public Dialog datePickerAlert() {
        return new AlertDialog.Builder(getContext())
                .setMessage("Please select a valid return date. The return date cannot be before the departure date.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).create();
    }

    public Dialog datePickerOneAlert() {
        return new AlertDialog.Builder(getContext())
                .setMessage("Please select a departure date.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).create();
    }

    public Dialog datePickerTwoAlert() {
        return new AlertDialog.Builder(getContext())
                .setMessage("Please select a return date.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOneWayDepartureDatePicker:
                datePickerDialog(ONE_WAY_DEPARTURE_DATE_PICKER).show();
                break;

        }

    }
}
