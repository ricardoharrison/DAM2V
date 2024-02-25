package com.rittz.namecolors;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar sbRed, sbGreen, sbBlue;
    TextView tvWhiteText, tvDisplay, tvRedBar, tvGreenBar, tvBlueBar;
    EditText etUserInput;
    Switch swWhiteText;

    final int WHITE = Color.rgb(255, 255, 255);
    final int BLACK = Color.rgb(0, 0, 0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swWhiteText = findViewById(R.id.switchWhiteText);
        sbBlue = findViewById(R.id.seekBarBlue);
        sbRed = findViewById(R.id.seekBarRed);
        sbGreen = findViewById(R.id.seekBarGreen);
        tvDisplay = findViewById(R.id.textViewDisplay);
        etUserInput = findViewById(R.id.editTextUserInput);
        tvRedBar = findViewById(R.id.textViewRed);
        tvGreenBar = findViewById(R.id.textViewGreen);
        tvBlueBar = findViewById(R.id.textViewBlue);

        etUserInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvDisplay.setText(etUserInput.getText());
                //tvDisplay.setBackgroundColor(Color.rgb(sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress()));
            }
        });

        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvDisplay.setBackgroundColor(Color.rgb(sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvDisplay.setBackgroundColor(Color.rgb(sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvDisplay.setBackgroundColor(Color.rgb(sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        swWhiteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swWhiteText.isChecked()){
                    tvDisplay.setTextColor(WHITE);
                } else {
                    tvDisplay.setTextColor(BLACK);
                }
            }
        });



    }
}