package com.rittz.lanzadoractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textViewCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle info = getIntent().getExtras();
        String sInfo = info.getString(MainActivity.INFO_NAME);

        TextView tv = findViewById(R.id.textViewCenter);
        tv.setText(tv.getText() + sInfo);
    }
}