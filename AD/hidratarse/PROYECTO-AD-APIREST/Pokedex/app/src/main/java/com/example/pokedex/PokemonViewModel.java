package com.example.pokedex;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pokedex.api.PokemonRepository;
import com.example.pokedex.data.DetalleResponse;
import com.example.pokedex.data.ListaResponse;

public class PokemonViewModel extends AndroidViewModel {
    private PokemonRepository pokemonRepository;
    private LiveData<DetalleResponse> pokemonDetalleLiveData;
    private LiveData<ListaResponse> pokemonListaLiveData;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        pokemonRepository = new PokemonRepository();
        pokemonDetalleLiveData = pokemonRepository.getPokemonDetalleLiveData();
        pokemonListaLiveData = pokemonRepository.getPokemonListaLiveData();
    }

    public void llamadaDetalle(String url) {
        pokemonRepository.llamadaDetalle(url);
    }

    public void llamadaLista(int limit){pokemonRepository.llamadaLista(limit);}

    public LiveData<DetalleResponse> getPokemonDetalleLiveData() {
        return pokemonDetalleLiveData;
    }

    public LiveData<ListaResponse> getPokemonListaLiveData() {
        return pokemonListaLiveData;
    }
}