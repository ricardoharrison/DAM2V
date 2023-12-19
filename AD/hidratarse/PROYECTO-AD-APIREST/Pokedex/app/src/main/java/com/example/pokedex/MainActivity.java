package com.example.pokedex;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pokedex.data.ListaResponse;

public class MainActivity extends AppCompatActivity {
    public static final String NOMBRE = "";
    RecyclerView recyclerView;
    PokemonAdapter adapter;
    PokemonViewModel pm;
    LiveData<ListaResponse> dataLista;
    public final int LIMITE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        recyclerView = findViewById(R.id.primerRV);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PokemonAdapter();

        recyclerView.setAdapter(adapter);

        pm = new ViewModelProvider(this).get(PokemonViewModel.class);
        pm.init();

        dataLista = pm.getPokemonListaLiveData();

        dataLista.observe(this, (dato)->{
            adapter.setListaResults(dato.getResults());
        });

        adapter.setClickListener((view, s) -> {
            Toast.makeText(MainActivity.this,"Pulsado "+ s, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra(NOMBRE,s);
            startActivity(intent);
        });

        pm.llamadaLista(LIMITE);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager  = (LinearLayoutManager) recyclerView.getLayoutManager();
                int total = layoutManager.getItemCount();
                int ultimoVisible = layoutManager.findLastVisibleItemPosition();
                if (total == (ultimoVisible + 1)) {
                    pm.llamadaLista(total+LIMITE);
                }
            }
        });
    }
}