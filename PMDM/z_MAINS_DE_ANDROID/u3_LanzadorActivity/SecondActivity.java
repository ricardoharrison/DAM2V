package com.rittz.lanzadoractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textViewCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();
        String myString = i.getStringExtra(MainActivity.INFO_NAME);

        textViewCenter = (TextView) findViewById(R.id.textViewCenter);
        if (myString != null) {
            textViewCenter.setText(myString);
        } else {
            textViewCenter.setText("Default Text");
        }


    }
}