package com.rittz.monster;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class Activity2 extends AppCompatActivity {
    TextView textViewShowMonster;
    static final int MAX_BRIGHTNESS = 190;

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

        //set background color
        int r = Color.red(serializedMonster.getColor());
        int g = Color.green(serializedMonster.getColor());
        int b = Color.blue(serializedMonster.getColor());
        if(r > MAX_BRIGHTNESS && g > MAX_BRIGHTNESS && b > MAX_BRIGHTNESS){
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }
    }
}