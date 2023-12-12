package com.rittz.datetimepickerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button buttonDate, buttonTime;
    TextView textViewResult;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDate = findViewById(R.id.buttonDate);
        buttonTime = findViewById(R.id.buttonTime);

        textViewResult = findViewById(R.id.textViewResult);

        buttonDate.setOnClickListener(view -> {
            // on below line we are getting the instance of our calendar.
            final Calendar myDate = Calendar.getInstance();

            // on below line we are getting our day, month and year.
            int year = myDate.get(Calendar.YEAR);
            int month = myDate.get(Calendar.MONTH);
            int day = myDate.get(Calendar.DAY_OF_MONTH);

            // on below line we are creating a variable for date picker dialog.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // on below line we are passing context.
                    MainActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            try {
                                myCalendar.set(year, monthOfYear, dayOfMonth);  //pasamos los valores a variable externa (myCalendar)
                                textViewResult.setText(myCalendar.getTime().toString());

                            } catch (Exception e){

                            }
                        }
                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        });

        buttonTime.setOnClickListener(view -> {
            // on below line we are getting the
            // instance of our calendar.
            final Calendar myTime
                    = Calendar.getInstance();

            // on below line we are getting our hour, minute.
            int hour = myTime.get(Calendar.HOUR_OF_DAY);
            int minute = myTime.get(Calendar.MINUTE);

            // on below line we are initializing our Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // on below line we are setting selected time
                            // in our text view.
                            try{
                                myCalendar = myTime;
                                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);    //pasamos los valores a variable externa (myCalendar)
                                myCalendar.set(Calendar.MINUTE, minute);
                                myCalendar.set(Calendar.SECOND, 0);

                                textViewResult.setText(myCalendar.getTime().toString());

                            } catch (NullPointerException npe) {

                            }
                        }
                    }, hour, minute, true);

            // at last we are calling show to
            // display our time picker dialog.
            timePickerDialog.show();
        });
    }
}