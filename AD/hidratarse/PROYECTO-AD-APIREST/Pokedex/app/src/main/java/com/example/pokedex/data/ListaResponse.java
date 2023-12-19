package com.example.pokedex.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaResponse {
    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("results")
    @Expose
    private List<PokemonResult> results;

    public List<PokemonResult> getResults() {
        return results;
    }

    public int getCount() {
        return count;
    }
}
