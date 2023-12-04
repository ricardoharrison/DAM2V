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
import android.widget.Toast;

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
                    int resultSent = data.getIntExtra(Activity2.INFO_RESULT, DEFAULT_VALUE);
                    String N1 = textViewN2.getText().toString();
                    String N2 = String.valueOf(resultSent);
                    textViewN1.setText(N1);
                    textViewN2.setText(N2);
                } else {
                    Toast.makeText(MainActivity.this, R.string.error_msg, Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonNext.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra(INFO_N1, textViewN1.getText().toString());
            intent.putExtra(INFO_N2, textViewN2.getText().toString());
            launcher.launch(intent);
        });
    }



}