package com.example.pokedex.api;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pokedex.data.DetalleResponse;
import com.example.pokedex.data.ListaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonRepository {
    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/";
    private PokemonService pokemonService;
    private MutableLiveData<DetalleResponse> pokemonDetalleLiveData;
    private MutableLiveData<ListaResponse> pokemonListaLiveData;

    public PokemonRepository() {
        pokemonDetalleLiveData = new MutableLiveData<>();

        pokemonListaLiveData = new MutableLiveData<>();

        pokemonService = new retrofit2.Retrofit.Builder()
                .baseUrl(POKEAPI_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonService.class);
    }

    public void llamadaDetalle(String url) {
        pokemonService.llamadaDetalle(url)
                .enqueue(new Callback<DetalleResponse>() {
                    @Override
                    public void onResponse(Call<DetalleResponse> call, Response<DetalleResponse> response) {
                        if (response.body() != null) {
                            Log.d(TAG,"BUSCANDO DATA 1");
                            pokemonDetalleLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<DetalleResponse> call, Throwable t) {
                        pokemonDetalleLiveData.postValue(null);
                    }
                });
    }

    public void llamadaLista(int limit){
        pokemonService.llamadaLista(limit)
                .enqueue(new Callback<ListaResponse>() {
                    @Override
                    public void onResponse(Call<ListaResponse> call, Response<ListaResponse> response) {
                        if (response.body() != null) {
                            Log.d(TAG,"BUSCANDO DATA 2");

                            pokemonListaLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ListaResponse> call, Throwable t) {
                        Log.d(TAG,"no hay respuesta");
                        pokemonListaLiveData.postValue(null);
                    }
                });
    }

    public MutableLiveData<ListaResponse> getPokemonListaLiveData() {
        return pokemonListaLiveData;
    }
    public LiveData<DetalleResponse> getPokemonDetalleLiveData() {
        return pokemonDetalleLiveData;
    }
}
