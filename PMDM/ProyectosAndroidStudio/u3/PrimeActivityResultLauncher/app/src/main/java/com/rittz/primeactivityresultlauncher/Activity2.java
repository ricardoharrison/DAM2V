package com.rittz.primeactivityresultlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {
    static final String INFO_NUMBER = "Activity2.INFO_NUMBER";
    Button buttonAccept, buttonCancel;
    EditText editTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        buttonAccept = findViewById(R.id.buttonAccept);
        buttonCancel = findViewById(R.id.buttonCancel);
        editTextNumber = findViewById(R.id.editTextNumber);

        buttonAccept.setOnClickListener(view -> {
            if(!editTextNumber.getText().toString().trim().isEmpty()){
                Intent data = new Intent();
                data.putExtra(INFO_NUMBER, editTextNumber.getText().toString());
                setResult(Activity2.RESULT_OK, data);
                finish();
            }
        });

        buttonCancel.setOnClickListener(view -> {
            setResult(Activity2.RESULT_CANCELED);
            finish();
        });

    }
}