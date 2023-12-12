package com.rittz.examen;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivityViajes extends AppCompatActivity {
    static final String INFO_VIAJE = "MainActivityViajes.viaje";
    Spinner spinnerOrigen, spinnerDestino;
    Button buttonFechaSalida, buttonfechaLlegada, buttonReservar;
    CheckBox checkBoxSoloIda;
    TextView textViewError;
    Viaje viaje;

    String errorMsg = "";

    Calendar fechaSalida = Calendar.getInstance();
    Calendar fechaLlegada = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viajes_main);

        spinnerOrigen = findViewById(R.id.spinnerOrigen);
        spinnerDestino = findViewById(R.id.spinnerDestino);
        buttonFechaSalida = findViewById(R.id.buttonFechaSalida);
        buttonfechaLlegada = findViewById(R.id.buttonFechaLlegada);
        buttonReservar = findViewById(R.id.buttonReservar);
        checkBoxSoloIda = findViewById(R.id.checkBoxSoloIda);
        textViewError = findViewById(R.id.textViewErrores);


        textViewError.setTextColor(Color.RED);

        ArrayList<Ciudades> listaCiudades = new ArrayList<>();
        for(Ciudades type : Ciudades.values()){
            listaCiudades.add(type);
        }

        ArrayAdapter<Ciudades> adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, listaCiudades);
        spinnerOrigen.setAdapter(adapter);
        spinnerDestino.setAdapter(adapter);



        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity2Viajes.RESULT_OK){ //REINICIAR
                    reiniciarCampos();
                } else if (result.getResultCode() == Activity2Viajes.RESULT_CANCELED){ //VOLVER
                    //no hacer nada
                }
            }
        });



        buttonFechaSalida.setOnClickListener(view -> {
            final Calendar myDate = Calendar.getInstance();


            // on below line we are getting our day, month and year.
            int year = myDate.get(Calendar.YEAR);
            int month = myDate.get(Calendar.MONTH);
            int day = myDate.get(Calendar.DAY_OF_MONTH);

            // on below line we are creating a variable for date picker dialog.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // on below line we are passing context.
                    MainActivityViajes.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            try {
                                fechaSalida.set(year, monthOfYear, dayOfMonth);

                            } catch (Exception e){

                            }
                        }
                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        });

        buttonfechaLlegada.setOnClickListener(view -> {
            final Calendar myDate = Calendar.getInstance();
            // on below line we are getting our day, month and year.
            int year = myDate.get(Calendar.YEAR);
            int month = myDate.get(Calendar.MONTH);
            int day = myDate.get(Calendar.DAY_OF_MONTH);

            // on below line we are creating a variable for date picker dialog.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // on below line we are passing context.
                    MainActivityViajes.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            try {
                                fechaLlegada.set(year, monthOfYear, dayOfMonth);

                            } catch (Exception e){

                            }
                        }
                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        });

        buttonReservar.setOnClickListener(view -> {
            errorMsg = "";
            if(comprobarDestino() && comprobarFechas(fechaSalida, fechaLlegada, checkBoxSoloIda.isChecked())){
                Intent intent = new Intent(MainActivityViajes.this, Activity2Viajes.class);
                if(checkBoxSoloIda.isChecked()){
                    viaje = new Viaje(spinnerOrigen.getSelectedItem().toString(), spinnerDestino.getSelectedItem().toString(), fechaSalida, null);
                } else {
                    viaje = new Viaje(spinnerOrigen.getSelectedItem().toString(), spinnerDestino.getSelectedItem().toString(), fechaSalida, fechaLlegada);
                }
                intent.putExtra(INFO_VIAJE, viaje);
                launcher.launch(intent);
            } else {
                textViewError.setText(errorMsg);
            }
        });

        checkBoxSoloIda.setOnClickListener(view -> {
            if(checkBoxSoloIda.isChecked()){
                buttonfechaLlegada.setEnabled(false);
            } else {
                buttonfechaLlegada.setEnabled(true);
            }
        });



    }

    private void reiniciarCampos() {
        spinnerOrigen.setSelection(0);
        spinnerDestino.setSelection(0);
        fechaSalida = Calendar.getInstance();
        fechaLlegada = Calendar.getInstance();
        checkBoxSoloIda.setChecked(false);
        buttonfechaLlegada.setEnabled(true);
    }

    public boolean comprobarDestino(){
        if(spinnerOrigen.getSelectedItem() == spinnerDestino.getSelectedItem()){
            errorMsg += getResources().getString(R.string.Error_destino) + "\n";
            return false;
        }
        return true;
    }
    public boolean comprobarFechas(Calendar fechaSalida, Calendar fechaLlegada, boolean soloIda){

        if(!soloIda){
            if(fechaSalida == null || fechaLlegada == null) {
                errorMsg += getResources().getString(R.string.Error_fechas) + "\n";
                return false;
            } else if (fechaSalida.getTimeInMillis() >= fechaLlegada.getTimeInMillis()){
                errorMsg += getResources().getString(R.string.Error_fechas) + "\n";
                return false;
            }
        } else {
            if(fechaSalida == null){
                return false;
            }
        }
        return true;
    }
}