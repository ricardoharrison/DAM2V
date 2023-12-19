package com.rittz.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button buttonRefresh;
    TestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TestAdapter(CountryItem.generateCountryItem(CountryItem.TOTAL_COUNTRIES));
        recyclerView.setAdapter(adapter);

        buttonRefresh = findViewById(R.id.buttonRefresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add(CountryItem.generateCountryItem(CountryItem.TOTAL_COUNTRIES));
            }
        });

        //adapter.notifyDataSetChanged();
    }
}