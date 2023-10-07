package com.example.greetenator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Definir atributos para controles
    Button btGreet;
    TextView tvOutput;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recoger las referencias a los controles de interfaz
        btGreet = findViewById(R.id.buttonGreet);
        tvOutput = findViewById(R.id.textViewOutput);
        etName = findViewById(R.id.editTextTextName);

        //Manejador de evento
        btGreet.setOnClickListener((View v) -> {
            String name = etName.getText().toString();
            if(name.equals("")) {
                tvOutput.setText(R.string.helloWorld);
            } else if (name.toLowerCase().equals(getResources().getString(R.string.world))) {
                tvOutput.setText(R.string.explode);
            } else {
                tvOutput.setText(getResources().getString(R.string.hello) + etName.getText());
            }
        });
    }




}