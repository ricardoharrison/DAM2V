package com.rittz.recyclerconclickyviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button buttonApiCall;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        buttonApiCall = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        ArrayList<Pelicula> peliculas = new ArrayList<>();
        //peliculas.add(new EmailItem("Walter", "heisenberg@mail.com", R.drawable.ic_launcher_background));

        MyViewModel vm = new ViewModelProvider(this).get(MyViewModel.class);




        progressBar.setVisibility(View.INVISIBLE);

        buttonApiCall.setOnClickListener((v)->{

            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            buttonApiCall.setEnabled(false);
            vm.loadArrayListPeliculas();
            //SI observe() va dentro del listener del botón, el recyclerView se carga cuando
            //se pinche el botón. Si estuviese fuera, se cargaría en el OnCreate(), es decir
            //al iniciar la aplicación
            vm.getArrayListPeliculas().observe(this, arrayList -> {
                // Actualizar la interfaz
                if(arrayList == MyViewModel.FAIL){
                    buttonApiCall.setText("Error"); //sustituir por algo que realmente avise del error
                }else{
                    Adapter adapter = new Adapter(arrayList, this);
                    recyclerView.setAdapter(adapter);
                }

                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                buttonApiCall.setEnabled(true);
            });
        });





    }
}