package com.rittz.radiospinnerlauncher;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private static final int MIN_NAME_LEN = 3;
    static final String INFO_STARTER = "Activity2.starter";
    Button buttonColor, buttonSubmit;
    TextView textViewDone, textViewWarning;
    EditText editTextName;

    Integer color = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        String receivedMainType = intent.getStringExtra(MainActivity.INFO_MAIN_TYPE);
        String receivedSecType = intent.getStringExtra(MainActivity.INFO_SEC_TYPE);

        buttonColor = findViewById(R.id.buttonColor);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        textViewDone = findViewById(R.id.textViewDone);
        textViewWarning = findViewById(R.id.textViewWarning);
        editTextName = findViewById(R.id.editTextName);

        textViewDone.setVisibility(View.GONE);

        buttonColor.setOnClickListener(view -> {
            textViewDone.setVisibility(View.VISIBLE);
            buttonColor.setEnabled(false);
            color = Color.rgb((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
        });


        buttonSubmit.setOnClickListener(view -> {
            String currentName = editTextName.getText().toString();
            boolean validateMessage = true;
            String message = "Your selection has the following issues:\n";
            if(currentName.trim().length() < MIN_NAME_LEN){
                message += ("\t- Name has to be at least " + MIN_NAME_LEN + " characters long\n");
                validateMessage = false;
            }
            for(int i = 0; i < currentName.length(); i++){
                if (!Character.isLetter(currentName.charAt(i))) {
                    message += ("\t- Name has to contain only letters\n");
                    validateMessage = false;
                    break;
                }
            }
            if(color == null){
                message += ("\t- You must generate a color before submitting\n");
                validateMessage = false;
            }

            if(validateMessage == false){
                textViewWarning.setText(message);
            }
            if(validateMessage){
                Starter starter = new Starter(editTextName.getText().toString(), PokemonStarterType.valueOf(receivedMainType),
                        SecondaryPokemonType.valueOf(receivedSecType), color);
                Intent data = new Intent();
                data.putExtra(INFO_STARTER, starter);
                setResult(RESULT_OK, data);
                finish();
            }
        });


    }
}