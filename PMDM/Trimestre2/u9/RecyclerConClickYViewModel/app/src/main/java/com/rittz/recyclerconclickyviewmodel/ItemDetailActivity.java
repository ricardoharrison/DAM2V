package com.rittz.recyclerconclickyviewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailActivity extends AppCompatActivity {
    TextView textViewDetail1, textViewDetail2, textViewDetail3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        textViewDetail1 = findViewById(R.id.textViewDetail1);
        textViewDetail2 = findViewById(R.id.textViewDetail2);
        textViewDetail3 = findViewById(R.id.textViewDetail3);

        Intent i = getIntent();
        textViewDetail1.setText(i.getStringExtra("INFO_1"));
        textViewDetail2.setText(i.getStringExtra("INFO_2"));
        textViewDetail3.setText(i.getStringExtra("INFO_3"));
    }
}