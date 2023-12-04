package com.rittz.evernotarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;


public class MainActivity extends AppCompatActivity {

    static final String INFO_NOTEBOOK = "allNotes";
    EditText editTextTitle, editTextBody;
    Button buttonAdd, buttonView;

    NoteBook noteBook = new NoteBook();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextBody = findViewById(R.id.editTextBody);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonView = findViewById(R.id.buttonView);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity2.RESULT_OK){

                } else if (result.getResultCode() == Activity2.RESULT_CANCELED){
                    Intent data = result.getData();
                    noteBook = (NoteBook) data.getSerializableExtra(MainActivity.INFO_NOTEBOOK);
                    setResult(RESULT_CANCELED, data);
                } else {
                    //otro error
                }

            }
        });

        buttonAdd.setOnClickListener(v -> {
            Note note = new Note(editTextTitle.getText().toString(), editTextBody.getText().toString(), Calendar.getInstance());
            noteBook.addNote(note);
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            intent.putExtra(INFO_NOTEBOOK, noteBook);
            launcher.launch(intent);
        });


    }
}