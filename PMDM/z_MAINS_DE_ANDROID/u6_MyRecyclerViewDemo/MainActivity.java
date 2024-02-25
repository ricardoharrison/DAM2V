package com.rittz.myrecyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        ArrayList<EmailItem> contacts = new ArrayList<>();
        contacts.add(new EmailItem("Walter", "heisenberg@mail.com", R.drawable.ic_launcher_background));
        contacts.add(new EmailItem("Jesse", "selling_some_stuff@mail.com", R.drawable.ic_launcher_background));
        contacts.add(new EmailItem("Saul Goodman", "its.all.good.man@mail.com", R.drawable.ic_launcher_background));
        contacts.add(new EmailItem("Mike", "mike_ehrmantraut@mail.com", R.drawable.ic_launcher_background));
        contacts.add(new EmailItem("Skyler", "its_skyler@mail.com", R.drawable.ic_launcher_background));
        contacts.add(new EmailItem("Hank", "sound_of_da_police@mail.com", R.drawable.ic_launcher_background));
        contacts.add(new EmailItem("Walter Jr.", "walterjunior@mail.com", R.drawable.ic_launcher_background));
        contacts.add(new EmailItem("Pollos Hermanos", "polloshermanos@mail.com", R.drawable.ic_launcher_background));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Adapter adapter = new Adapter(contacts, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}