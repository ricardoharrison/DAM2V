package com.rittz.fibonaccilauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;


public class Activity2 extends AppCompatActivity {

    Button buttonAccept;
    TextView textViewResult;
    static final String INFO_RESULT = "Activity2.result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        String receivedN1 = intent.getStringExtra(MainActivity.INFO_N1);
        String receivedN2 = intent.getStringExtra(MainActivity.INFO_N2);
        System.out.println("n1, n2 = " + receivedN1 + receivedN2);
        int n1 = Integer.parseInt(receivedN1);
        int n2 = Integer.parseInt(receivedN2);

        int result = n1 + n2;

        textViewResult = findViewById(R.id.textViewResult);

        textViewResult.setText(String.valueOf(result));

        buttonAccept = findViewById(R.id.buttonAccept);

        buttonAccept.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(INFO_RESULT, result);
            setResult(Activity2.RESULT_OK, data);
            finish();
        });
    }
}