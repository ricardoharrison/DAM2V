package com.rittz.icecream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerContainer;
    Button buttonOrder;
    EditText editTextVanilla, editTextStrawberry, editTextChocolate;
    final static String CONE_CONTAINER_NAME = "Cone", CHOCO_CONE_CONTAINER_NAME = "Choco Cone", SUNDAE_CONTAINER_NAME = "Sundae";
    final static String INFO_VANILLA_AMOUNT = "MainActivity.vanillaAmount", INFO_STRAWBERRY_AMOUNT = "MainActivity.vanillaAmount",INFO_CHOCOLATE_AMOUNT = "MainActivity.vanillaAmount", INFO_SELECTED_CONTAINER = "MainActivity.selectedContainer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creando spinner
        spinnerContainer = findViewById(R.id.spinnerContainer);
        ArrayList<Container> containersList = new ArrayList<>();
        containersList.add(new Container(CONE_CONTAINER_NAME));
        containersList.add(new Container(CHOCO_CONE_CONTAINER_NAME));
        containersList.add(new Container(SUNDAE_CONTAINER_NAME));

        ArrayAdapter<Container> adapterContainer = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, containersList);
        spinnerContainer.setAdapter(adapterContainer);

        buttonOrder = findViewById(R.id.buttonOrder);
        editTextVanilla = findViewById(R.id.editTextVanilla);
        editTextStrawberry = findViewById(R.id.editTextStrawberry);
        editTextChocolate = findViewById(R.id.editTextChocolate);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer vanillaAmount = null, strawberryAmount = null, chocolateAmount = null;
                try{
                    vanillaAmount = Integer.parseInt(editTextVanilla.getText().toString());
                    strawberryAmount = Integer.parseInt(editTextStrawberry.getText().toString());
                    chocolateAmount = Integer.parseInt(editTextChocolate.getText().toString());
                } catch (Exception e){}
                Container selectedContainer = (Container) spinnerContainer.getSelectedItem();
                Intent i = new Intent(MainActivity.this, Activity2.class);
                i.putExtra(INFO_VANILLA_AMOUNT, vanillaAmount);
                i.putExtra(INFO_STRAWBERRY_AMOUNT, strawberryAmount);
                i.putExtra(INFO_CHOCOLATE_AMOUNT, chocolateAmount);
                i.putExtra(INFO_SELECTED_CONTAINER, selectedContainer.toString());
                startActivity(i);
            }
        });





    }
}