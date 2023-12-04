package com.rittz.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    static final String INFO_RESULT = "com.rittz.calculator.INFO_RESULT";
    Button buttonCalculate;
    EditText editTextFirstOperator, editTextSecondOperator;
    RadioButton radioAdd, radioSubtract, radioMultiply, radioDivide;
    Integer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalculate = findViewById(R.id.buttonCalculate);
        editTextFirstOperator = findViewById(R.id.editTextFirstOperator);
        editTextSecondOperator = findViewById(R.id.editTextSecondOperator);
        radioAdd = findViewById(R.id.radioButtonAdd);
        radioSubtract = findViewById(R.id.radioButtonSubtract);
        radioMultiply = findViewById(R.id.radioButtonMultiply);
        radioDivide = findViewById(R.id.radioButtonDivide);

        radioAdd.setChecked(true);

        buttonCalculate.setOnClickListener(view -> {
            Integer firstOperator = null, secondOperator = null;
            try{
                firstOperator = Integer.parseInt(editTextFirstOperator.getText().toString());
                secondOperator = Integer.parseInt(editTextSecondOperator.getText().toString());
            } catch (Exception e){}

            if(firstOperator != null && secondOperator != null){
                if(radioAdd.isChecked()){
                    result = firstOperator + secondOperator;
                } else if(radioSubtract.isChecked()) {
                    result = firstOperator - secondOperator;
                } else if(radioMultiply.isChecked()) {
                    result = firstOperator * secondOperator;
                } else if(radioDivide.isChecked() && secondOperator != 0) {
                    result = firstOperator / secondOperator;
                }
                Intent i = new Intent(this, Activity2.class);
                i.putExtra(INFO_RESULT, result);
                startActivity(i);
            }

        });
    }
}