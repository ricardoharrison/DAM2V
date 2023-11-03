package com.rittz.letteranalysis;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String INFO_TEXT = "mainActivity.myText";
    Button buttonAnalyse;
    EditText editTextSubmitted;
    TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAnalyse = findViewById(R.id.buttonAnalyse);
        editTextSubmitted = findViewById(R.id.editTextText);
        textViewInfo = findViewById(R.id.textViewInfo);


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity2.RESULT_OK){
                    Intent data = result.getData();
                    //[ViewVaribaleName].setText(data.getStringExtra([ActivityName].[CONSTANT]));
                } if (result.getResultCode() == Activity2.RESULT_CANCELED){

                } else {
                    //otro error
                }

            }
        });

        buttonAnalyse.setOnClickListener(view -> {
            if(!editTextSubmitted.getText().toString().trim().isEmpty()){
                Intent intent = new Intent(this, Activity2.class);
                String myText = editTextSubmitted.getText().toString();
                intent.putExtra(INFO_TEXT, myText);
                launcher.launch(intent);
            }


        });
    }
}