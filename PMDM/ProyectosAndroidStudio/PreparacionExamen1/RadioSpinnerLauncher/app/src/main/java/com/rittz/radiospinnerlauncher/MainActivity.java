package com.rittz.radiospinnerlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String INFO_MAIN_TYPE = "MainActivity.selectedMainType";
    static final String INFO_SEC_TYPE = "MainActivity.selectedSecType";
    RadioButton buttonGrass, buttonFire, buttonWater;
    Button buttonContinue;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SPINNER
        ArrayList<SecondaryPokemonType> containersList = new ArrayList<>();

        ArrayAdapter<SecondaryPokemonType> adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, containersList);
        spinner.setAdapter(adapter);


        //BUTTONS
        buttonGrass = findViewById(R.id.radioButtonGrass);
        buttonFire = findViewById(R.id.radioButtonFire);
        buttonWater = findViewById(R.id.radioButtonWater);
        buttonContinue = findViewById(R.id.buttonContinue);

        buttonGrass.setTag(PokemonStarterType.GRASS);
        buttonFire.setTag(PokemonStarterType.FIRE);
        buttonWater.setTag(PokemonStarterType.WATER);



        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity2.RESULT_OK){
                    Intent data = result.getData();
                    //EJEMPLO: [ViewVariableName].setText(data.getStringExtra([Activity2].[CONSTANT]));
                } else if (result.getResultCode() == Activity2.RESULT_CANCELED){

                } else {
                    //otro error
                }

            }
        });



        buttonContinue.setOnClickListener(view -> {
            RadioButton selectedRadioButton = null;
            if(buttonGrass.isChecked()){
                selectedRadioButton = buttonGrass;
            }
            if(buttonGrass.isChecked()){
                selectedRadioButton = buttonGrass;
            }
            if(buttonGrass.isChecked()){
                selectedRadioButton = buttonGrass;
            }
            if(selectedRadioButton != null){
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                String selectedMainType = selectedRadioButton.getTag().toString();
                String selectedSecondaryType = spinner.getSelectedItem().toString();
                intent.putExtra(INFO_MAIN_TYPE, selectedMainType);
                intent.putExtra(INFO_SEC_TYPE, selectedSecondaryType);
                launcher.launch(intent);
            }
        });



    }
}