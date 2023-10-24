package com.rittz.primeactivityresultlauncher;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button buttonObtain;
    TextView textViewNumber, textViewMsg;
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

                } if (result.getResultCode() == Activity.RESULT_OK){

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