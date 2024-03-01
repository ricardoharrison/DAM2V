package com.rittz.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static final String ERROR_MSG = "Error en el acceso a los datos";
    TextView textViewPoema;
    Button buttonRecitar;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPoema = findViewById(R.id.textViewPoema);
        buttonRecitar = findViewById(R.id.buttonRecitar);
        progressBar = findViewById(R.id.progressBar);

        MyViewModel vm = new ViewModelProvider(this).get(MyViewModel.class);

        progressBar.setVisibility(View.INVISIBLE);

        buttonRecitar.setOnClickListener((v)->{
            progressBar.setVisibility(View.VISIBLE);
            buttonRecitar.setEnabled(false);
            vm.getPoema().observe(this, texto -> {
                // Actualizar la interfaz
                if(texto == MyViewModel.FAIL){
                    textViewPoema.setText(ERROR_MSG);
                }else{
                    textViewPoema.setText(texto);

                }
            });
            vm.loadPoema();


        });

        buttonRecitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void enableGuiItems(){
        progressBar.setVisibility(View.INVISIBLE);
        buttonRecitar.setEnabled(true);
    }
}