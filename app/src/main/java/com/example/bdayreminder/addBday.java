package com.example.bdayreminder;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class addBday extends AppCompatActivity {
    Button submit;
    Button date;
    EditText input;
    String timeTonotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bday);

        input = (EditText) findViewById(R.id.input_details);
        submit = (Button) findViewById(R.id.submit_btn);
        date = (Button) findViewById(R.id.date_btn);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                String title = input.getText().toString().trim();//get name from editText

                String idate = date.getText().toString().trim(); //get date

                if (title.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter text", Toast.LENGTH_SHORT).show();

                } else {

                    if (date.equals("date")) {

                        Toast.makeText(getApplicationContext(), "Please select date and time", Toast.LENGTH_SHORT).show();

                    } else {

                        processinsert(title,idate); //add values to database
                    }
                }

            }

        });
    }

    private void processinsert(String title, String date) {

        String result = new dbManager(this).addreminder(title, date);//insert values to database
        //setAlarm(title, date, time);
        input.setText("");
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

    }


    private void selectDate() {
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.setText(day + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }



}
