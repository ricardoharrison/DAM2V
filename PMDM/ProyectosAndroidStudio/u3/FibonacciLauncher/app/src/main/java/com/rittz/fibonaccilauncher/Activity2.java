package com.rittz.fibonaccilauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;


public class Activity2 extends AppCompatActivity {

    Button buttonAccept;
    static final String INFO_RESULT = "Activity2.result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        int n1 = Integer.parseInt(intent.getStringExtra(MainActivity.INFO_N1));
        int n2 = Integer.parseInt(intent.getStringExtra(MainActivity.INFO_N2));

        int result = n1 + n2;

        buttonAccept = findViewById(R.id.buttonAccept);

        buttonAccept.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(INFO_RESULT, result);
            setResult(Activity2.RESULT_OK, data);
        });
    }
}