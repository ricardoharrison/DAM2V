package com.rittz.evernotarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;


public class Activity2 extends AppCompatActivity {

    TextView textViewDisplay;
    NoteBook notebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textViewDisplay = findViewById(R.id.textViewDisplay);

        Intent intent = getIntent();
        notebook = (NoteBook) intent.getSerializableExtra(MainActivity.INFO_NOTEBOOK);

        textViewDisplay.setText(notebook.toString());

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Back is pressed... Finishing the activity
                Intent intent = new Intent();
                intent.putExtra(MainActivity.INFO_NOTEBOOK, notebook);
                setResult(Activity2.RESULT_CANCELED, intent);
                finish();
            }
        });

    }


}