package com.rittz.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Activity2Viajes extends AppCompatActivity {
    TextView textViewMostrar;
    Button buttonVolver, buttonReiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_viajes);

        textViewMostrar = findViewById(R.id.textViewMostrar);
        buttonVolver = findViewById(R.id.buttonVolver);
        buttonReiniciar = findViewById(R.id.buttonReiniciar);

        Intent intent = getIntent();

        Viaje viajeRecibido = (Viaje) intent.getSerializableExtra(MainActivityViajes.INFO_VIAJE);

        textViewMostrar.setText(viajeRecibido.toString());

        buttonVolver.setOnClickListener(v -> {
            setResult(Activity2Viajes.RESULT_CANCELED);
            finish();
        });

        buttonReiniciar.setOnClickListener(v -> {
            setResult(Activity2Viajes.RESULT_OK);
            finish();
        });


    }
}