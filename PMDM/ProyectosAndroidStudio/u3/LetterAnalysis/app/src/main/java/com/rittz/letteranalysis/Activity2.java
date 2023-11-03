package com.rittz.letteranalysis;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    Button buttonReturn;
    TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        buttonReturn = findViewById(R.id.buttonReturn);
        textViewDisplay = findViewById(R.id.textViewDisplay);

        Intent intent = getIntent();
        String receivedText = intent.getStringExtra(MainActivity.INFO_TEXT);



    }
}