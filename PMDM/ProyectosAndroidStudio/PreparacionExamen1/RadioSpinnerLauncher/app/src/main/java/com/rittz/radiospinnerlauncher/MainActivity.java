package com.rittz.radiospinnerlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioButton buttonGrass, buttonFire, buttonWater;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SPINNER
        ArrayList<SecondaryPokemonType> containersList = new ArrayList<>();

        ArrayAdapter<SecondaryPokemonType> adapterContainer = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, containersList);
        spinner.setAdapter(adapterContainer);



        SecondaryPokemonType selectedSecondaryType = (SecondaryPokemonType) spinner.getSelectedItem();
    }
}