package com.rittz.lanzadoractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String INFO_NAME = "Elber";
    Button buttonLaunch;
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLaunch = findViewById(R.id.buttonLaunch);
        editTextName = findViewById(R.id.editTextName);

        buttonLaunch.setOnClickListener(view -> {
            Intent i = new Intent(this,SecondActivity.class);
            i.putExtra(INFO_NAME, editTextName.getText());
            startActivity(i);
        });
    }
}