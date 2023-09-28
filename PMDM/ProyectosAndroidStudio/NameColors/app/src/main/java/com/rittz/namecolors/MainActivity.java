package com.rittz.namecolors;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar sbBlue, sbRed, sbGreen;
    TextView tvWhiteText, tvDisplay;
    EditText etUserInput;
    Switch swWhiteText;


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

        etUserInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvDisplay.setText("--");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvDisplay.setText(etUserInput.getText());
                tvDisplay.setBackgroundColor(Color.rgb(sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress()));
            }
        });



    }
}