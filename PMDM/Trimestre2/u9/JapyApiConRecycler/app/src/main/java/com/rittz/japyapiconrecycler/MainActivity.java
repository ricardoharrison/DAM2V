package com.rittz.japyapiconrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button buttonFilms;
    Adapter adapter; // Declare adapter as a field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        buttonFilms = findViewById(R.id.buttonFilms);

        buttonFilms.setOnClickListener((v) -> {
            PeliculaService service = PeliculaService.getInstance();
            Call<List<Pelicula>> llamada = service.getRepo().getPeliculas();

            llamada.enqueue(new Callback<List<Pelicula>>() {
                @Override
                public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Información encontrada", Toast.LENGTH_LONG).show();
                        List<Pelicula> peliculas = response.body();
                        if (peliculas != null) {
                            // Create an ArrayList to store the movie data
                            ArrayList<Pelicula> elements = new ArrayList<>(peliculas);

                            // Initialize the adapter with the fetched data
                            adapter = new Adapter(elements, getApplicationContext());

                            // Set the adapter to the RecyclerView
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error al buscar la información", Toast.LENGTH_LONG).show();
                }
            });
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }
}