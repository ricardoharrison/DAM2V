package com.rittz.monster;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class Activity2 extends AppCompatActivity {
    TextView textViewShowMonster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textViewShowMonster = findViewById(R.id.textViewShowMonster);

        Intent intent = getIntent();
        Serializable receivedMonster;
        receivedMonster = intent.getSerializableExtra(MainActivity.INFO_MONSTER);

        Monster serializedMonster = (Monster) receivedMonster;

        textViewShowMonster.setTextColor(serializedMonster.getColor());

        textViewShowMonster.setText(serializedMonster.toString());
    }
}