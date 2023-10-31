package com.rittz.primeactivityresultlauncher;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonObtain;
    TextView textViewNumber, textViewMsg;

    private boolean isPrime(int number) {
        if(number <= 1){
            return false;
        }
        for(int i = 2; i * i <= number; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonObtain = findViewById(R.id.buttonObtain);
        textViewNumber = findViewById(R.id.textViewNumber);
        textViewMsg = findViewById(R.id.textViewMsg);


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    try{
                        String receivedNumber = data.getStringExtra(Activity2.INFO_NUMBER);
                        textViewNumber.setText(receivedNumber);
                        int receivedParsedNumber = Integer.parseInt(receivedNumber);
                        if(isPrime(receivedParsedNumber)){
                            textViewMsg.setText(R.string.IS_PRIME);
                        } else {
                            textViewMsg.setText(R.string.IS_NOT_PRIME);
                        }
                    } catch (Exception e) {}

                } if (result.getResultCode() == Activity.RESULT_CANCELED){
                    Toast.makeText(MainActivity.this, R.string.OP_CANCEL_MSG, Toast.LENGTH_LONG).show();
                } else {
                    //otro error
                }

            }

        });

        buttonObtain.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            launcher.launch(intent);
        });
    }
}