package com.rittz.apipokemon;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonService {

    private static PokemonService instance;
    private static PokemonRepo repo;

    private PokemonService(){
        // Hago cosas
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(PokemonRepo.class);
    }

    public static PokemonRepo getRepo(){
        return repo;
    }

    public static PokemonService getInstance(){
        if(instance == null){
            instance =  new PokemonService();
        }
        return instance;
    }
}