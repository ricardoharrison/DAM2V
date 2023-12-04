package com.rittz.propinator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_DISPLAYED_DIGITS = 8;
    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btC, btD, btCalculate;
    RadioButton rbBad, rbOk, rbGood;
    TextView tvDisplay, tvFinalCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = findViewById(R.id.button1);
        bt2 = findViewById(R.id.button2);
        bt3 = findViewById(R.id.button3);
        bt4 = findViewById(R.id.button4);
        bt5 = findViewById(R.id.button5);
        bt6 = findViewById(R.id.button6);
        bt7 = findViewById(R.id.button7);
        bt8 = findViewById(R.id.button8);
        bt9 = findViewById(R.id.button9);
        bt0 = findViewById(R.id.button0);
        btC = findViewById(R.id.buttonC);
        btD = findViewById(R.id.buttonD);
        btCalculate = findViewById(R.id.buttonCalculate);

        rbBad = findViewById(R.id.radioButtonBad);
        rbOk = findViewById(R.id.radioButtonOk);
        rbGood = findViewById(R.id.radioButtonGood);

        tvDisplay = findViewById(R.id.textViewDisplay);
        tvFinalCalc = findViewById(R.id.textViewFinalCalc);

        OnClickListener numericButtonsHandler = (View view) -> {
            Button button = (Button) view;
            String previousText = tvDisplay.getText().toString();
            if(previousText.length() < MAX_DISPLAYED_DIGITS){
                String newText = previousText + button.getText();
                tvDisplay.setText(newText);
            }

        };
        
        bt1.setOnClickListener(numericButtonsHandler);
        bt2.setOnClickListener(numericButtonsHandler);
        bt3.setOnClickListener(numericButtonsHandler);
        bt4.setOnClickListener(numericButtonsHandler);
        bt5.setOnClickListener(numericButtonsHandler);
        bt6.setOnClickListener(numericButtonsHandler);
        bt7.setOnClickListener(numericButtonsHandler);
        bt8.setOnClickListener(numericButtonsHandler);
        bt9.setOnClickListener(numericButtonsHandler);
        bt0.setOnClickListener(numericButtonsHandler);

        btC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDisplay.setText("");
            }
        });

        btD.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvDisplay.getText().length() > 0){
                    String previousText = tvDisplay.getText().toString();
                    String newText = previousText.substring(0, previousText.length() - 1);
                    tvDisplay.setText(newText);
                }
            }
        });

        btCalculate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvDisplay.getText().length() > 0){
                    String finalText;
                    float parsedFloat = Integer.parseInt(tvDisplay.getText().toString());
                    if (rbBad.isChecked()) {
                        parsedFloat = 0;
                    } else if (rbOk.isChecked()) {
                        parsedFloat *= 0.05;
                    } else if (rbGood.isChecked()) {
                        parsedFloat *= 0.1;
                    }
                    if(rbBad.isChecked() || rbOk.isChecked() || rbGood.isChecked()){
                        finalText = String.format("%.2f€", parsedFloat);
                        tvFinalCalc.setText(finalText);
                    }
                }
            }
        });
    }
}