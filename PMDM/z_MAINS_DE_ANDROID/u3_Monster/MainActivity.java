package com.rittz.monster;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int MAX_RGB_VALUE = 255;
    static final int MAX_NUMBER_OF_LIMBS = 30;
    static final String INFO_MONSTER = "MainActivity.monster";
    Button buttonCreate;
    EditText editTextName, editTextLimbs;
    SeekBar seekBarRed, seekBarGreen, seekBarBlue;
    TextView textViewColorSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCreate = findViewById(R.id.buttonCreate);
        editTextName = findViewById(R.id.editTextName);
        editTextLimbs = findViewById(R.id.editTextLimbs);
        seekBarRed = findViewById(R.id.seekBarRed);
        seekBarGreen = findViewById(R.id.seekBarGreen);
        seekBarBlue = findViewById(R.id.seekBarBlue);
        textViewColorSelection = findViewById(R.id.textViewColorSelection);

        //defining range of seekBar objects
        seekBarRed.setMax(MAX_RGB_VALUE);
        seekBarGreen.setMax(MAX_RGB_VALUE);
        seekBarBlue.setMax(MAX_RGB_VALUE);

        //setting random color
        seekBarRed.setProgress((int)(Math.random() * MAX_RGB_VALUE));
        seekBarGreen.setProgress((int)(Math.random() * MAX_RGB_VALUE));
        seekBarBlue.setProgress((int)(Math.random() * MAX_RGB_VALUE));

        //showing the color selection
        textViewColorSelection.setBackgroundColor(Color.rgb(
                                seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress()));

        SeekBar.OnSeekBarChangeListener seekBarHandler = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewColorSelection.setBackgroundColor(Color.rgb(
                        seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        seekBarRed.setOnSeekBarChangeListener(seekBarHandler);
        seekBarGreen.setOnSeekBarChangeListener(seekBarHandler);
        seekBarBlue.setOnSeekBarChangeListener(seekBarHandler);

        buttonCreate.setOnClickListener(view -> {
            if(editTextName.getText().toString().trim().isEmpty()){
                //alertDialog
            } else if (editTextLimbs.getText().toString().trim().isEmpty() ||
                            Integer.parseInt(editTextLimbs.getText().toString()) > MAX_NUMBER_OF_LIMBS){
                //alertDialog
            } else {
                //creating Monster object
                String name = editTextName.getText().toString();
                String numberOfLimbs = editTextLimbs.getText().toString();
                int intNumberOfLimbs = Integer.parseInt(numberOfLimbs);
                int color = Color.rgb(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
                Monster monster = new Monster(name, intNumberOfLimbs, color);

                //launching Activity2
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra(INFO_MONSTER, monster);
                startActivity(intent);
            }

        });


    }
}