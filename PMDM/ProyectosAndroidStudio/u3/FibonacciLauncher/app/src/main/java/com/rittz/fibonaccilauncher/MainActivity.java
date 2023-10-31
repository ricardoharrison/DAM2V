package com.rittz.fibonaccilauncher;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String INFO_N1 = "MainActivity.N1", INFO_N2 = "MainActivity.N2";
    Button buttonNext;
    TextView textViewN1, textViewN2;
    final static String STARTING_VALUE = "1";
    final static int DEFAULT_VALUE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNext = findViewById(R.id.buttonNext);
        textViewN1 = findViewById(R.id.textViewN1);
        textViewN2 = findViewById(R.id.textViewN2);

        textViewN1.setText(STARTING_VALUE);
        textViewN2.setText(STARTING_VALUE);


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity2.RESULT_OK){
                    Intent data = result.getData();
                    data.putExtra(Activity2.INFO_RESULT, result);
                }
            }
        });

        buttonNext.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra(INFO_N1, textViewN1.getText().toString());
            intent.putExtra(INFO_N2, textViewN1.getText().toString());
            startActivity(intent);
        });
    }



}