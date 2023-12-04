package com.rittz.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    static final int DEFAULT_VALUE = 0;
    static final int MAX_RESULT_SIZE = 999_999_999;
    static final int NUMBER_TOO_LONG_MSG = R.string.number_too_long_msg;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textViewResult = findViewById(R.id.textViewResult);

        Intent i = getIntent();
        int result = i.getIntExtra(MainActivity.INFO_RESULT, DEFAULT_VALUE);
        if(result < MAX_RESULT_SIZE){
            textViewResult.setText(String.valueOf(result));
        } else {
            textViewResult.setText(NUMBER_TOO_LONG_MSG);
        }
    }
}