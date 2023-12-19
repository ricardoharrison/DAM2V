package com.example.pokedex.api;

import com.example.pokedex.data.DetalleResponse;
import com.example.pokedex.data.ListaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonService {
    @GET("pokemon/{nombre}")
    Call<DetalleResponse> llamadaDetalle(@Path("nombre") String nombre);

    @GET("pokemon")
    Call<ListaResponse> llamadaLista(
            @Query("limit") int limit
    );
}
