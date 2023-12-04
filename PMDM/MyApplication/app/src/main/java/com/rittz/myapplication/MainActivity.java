package com.rittz.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewAgregado;
    FragmentCuenta fragment1;
    FragmentCuenta fragment2;

    final String INITIAL_TV_TEXT = "Total: ", NINOS = "Niños", ADULTOS = "Adultos";
    int totalAdultos = 0, totalNinos = 0;
    int totalPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = (FragmentCuenta) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        fragment1 = (FragmentCuenta) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);

        fragment1.setName(ADULTOS);
        fragment2.setName(NINOS);

    }

    private void actualizarPuntos() {
        String texto = "";
        for(int i = 0; i < totalPersonas; i++){
            texto += ".";
        }
    }


    public void addPerson(FragmentCuenta fragment, int total) {
        if(fragment == fragment1){
            totalAdultos = total;
        }
        if(fragment == fragment2){
            totalNinos = total;
        }
        totalPersonas = totalNinos + totalAdultos;
        textViewAgregado.setText(INITIAL_TV_TEXT + String.valueOf(totalPersonas));

    }
}