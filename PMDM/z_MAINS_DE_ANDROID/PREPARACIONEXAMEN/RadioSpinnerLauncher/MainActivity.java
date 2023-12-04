package com.rittz.radiospinnerlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

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
    Spinner spinnerSecTypes;
    TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SPINNER
        spinnerSecTypes = findViewById(R.id.spinnerSecTypes);
        ArrayList<SecondaryPokemonType> secTypesList = new ArrayList<>();
        for(SecondaryPokemonType type : SecondaryPokemonType.values()){
            secTypesList.add(type);
        }

        ArrayAdapter<SecondaryPokemonType> adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, secTypesList);
        spinnerSecTypes.setAdapter(adapter);


        //BUTTONS
        buttonGrass = findViewById(R.id.radioButtonGrass);
        buttonFire = findViewById(R.id.radioButtonFire);
        buttonWater = findViewById(R.id.radioButtonWater);
        buttonContinue = findViewById(R.id.buttonContinue);

        textViewDisplay = findViewById(R.id.textViewDisplay);

        buttonGrass.setTag(PokemonStarterType.GRASS);
        buttonFire.setTag(PokemonStarterType.FIRE);
        buttonWater.setTag(PokemonStarterType.WATER);



        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity2.RESULT_OK){
                    Intent data = result.getData();
                    Starter starter = (Starter) data.getSerializableExtra(Activity2.INFO_STARTER);
                    textViewDisplay.setText(starter.toString());


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
            if(buttonFire.isChecked()){
                selectedRadioButton = buttonFire;
            }
            if(buttonWater.isChecked()){
                selectedRadioButton = buttonWater;
            }
            if(selectedRadioButton != null){
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                String selectedMainType = String.valueOf(selectedRadioButton.getTag());
                String selectedSecondaryType = spinnerSecTypes.getSelectedItem().toString();
                intent.putExtra(INFO_MAIN_TYPE, selectedMainType);
                intent.putExtra(INFO_SEC_TYPE, selectedSecondaryType);
                launcher.launch(intent);
            }
        });



    }
}