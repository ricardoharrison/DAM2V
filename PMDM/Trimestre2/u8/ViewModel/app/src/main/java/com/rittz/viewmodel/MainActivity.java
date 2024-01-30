package com.rittz.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    TextView textViewNumber;
    Button buttonGenerate;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumber = findViewById(R.id.textViewNumber);
        buttonGenerate = findViewById(R.id.buttonGenerate);
        progressBar = findViewById(R.id.progressBar);

        MyViewModel vm = new ViewModelProvider(this).get(MyViewModel.class);

        vm.getNumber().observe(this, integer -> {
            // Actualizar la interfaz
            if(integer == MyViewModel.FAIL){
                textViewNumber.setText("Error en el acceso a los datos");
            }else{
                textViewNumber.setText(""+integer);
            }

            progressBar.setVisibility(View.INVISIBLE);
            textViewNumber.setVisibility(View.VISIBLE);
            buttonGenerate.setEnabled(true);
        });
        buttonGenerate.setEnabled(false);

        buttonGenerate.setOnClickListener((v)->{

            progressBar.setVisibility(View.VISIBLE);
            textViewNumber.setVisibility(View.INVISIBLE);
            buttonGenerate.setEnabled(false);
            vm.loadNumber();
        });
    }
}