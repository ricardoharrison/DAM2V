package com.rittz.citas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // on below line we are creating variables.
    Button pickDateBtn, pickTimeBtn, btSubmit;
    EditText etId;
    Calendar myDate;
    Calendar myTime;
    LocalTime openingTime = LocalTime.of(9, 30);
    LocalTime closingTime = LocalTime.of(20, 15);
    Boolean datePicked = false;
    Boolean timePicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on below line we are initializing our variables.
        pickDateBtn = findViewById(R.id.idBtnPickDate);
        pickTimeBtn = findViewById(R.id.idBtnPickTime);
        btSubmit = findViewById(R.id.buttonSubmit);
        etId = findViewById(R.id.editTextID);
        final String REGEX_ID = getResources().getString(R.string.regex_id);
        myDate = Calendar.getInstance();
        myTime = Calendar.getInstance();

        // on below line we are adding click listener for our pick date button
        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                try {
                                    myDate.set(year, monthOfYear, dayOfMonth);
                                } catch (Exception e){

                                }
                                datePicked = true;
                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        pickTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                try{
                                    myTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    myTime.set(Calendar.MINUTE, minute);
                                } catch (NullPointerException npe) {

                                }
                                timePicked = true;
                            }
                        }, hour, minute, true);

                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalTime selectedTime = LocalTime.of(0, 0);
                LocalDate selectedDate = LocalDate.of(1970, 1, 1);
                int selectedDayOfWeek = -1;

                try {
                    selectedTime = LocalTime.of(myTime.get(Calendar.HOUR_OF_DAY), myTime.get(Calendar.MINUTE));
                    selectedDate = LocalDate.of(myDate.get(Calendar.YEAR), myDate.get(Calendar.MONTH), myDate.get(Calendar.DAY_OF_MONTH));
                    selectedDayOfWeek = myDate.get(Calendar.DAY_OF_WEEK);
                } catch (NullPointerException npe) {

                }
                if (!datePicked || !timePicked) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(getResources().getString(R.string.alert_null_title))
                            .setMessage(getResources().getString(R.string.alert_null_msg))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                    ;
                } else if (!etId.getText().toString().matches(REGEX_ID)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(getResources().getString(R.string.alert_id_regex_title))
                            .setMessage(getResources().getString(R.string.alert_id_regex_msg))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else if (selectedTime.isBefore(openingTime) || selectedTime.isAfter(closingTime)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(getResources().getString(R.string.alert_closed_title))
                            .setMessage(getResources().getString(R.string.alert_closed_msg))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else if (selectedDate.isAfter(LocalDate.now())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(getResources().getString(R.string.alert_date_in_past_title))
                            .setMessage(getResources().getString(R.string.alert_date_in_past_msg))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else if (selectedDayOfWeek == Calendar.SATURDAY || selectedDayOfWeek == Calendar.SUNDAY) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(getResources().getString(R.string.alert_weekend_title))
                            .setMessage(getResources().getString(R.string.alert_weekend_msg))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else {
                    // After replacing the fragment
                    MyFragment myFragment = new MyFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, myFragment)
                            .addToBackStack(null)
                            .commit();

                    // Update the TextView within the fragment
                    String finalMessage = String.format(getResources().getString(R.string.final_msg), selectedDate.getDayOfMonth(), selectedDate.getMonthValue(),
                            selectedDate.getYear(), selectedTime.getHour(), selectedTime.getMinute());
                    myFragment.updateFinalMsg(finalMessage);

                }
            }
        });


    }
}
