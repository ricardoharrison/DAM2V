package com.example.pokedex.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetalleResponse {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("sprites")
    @Expose
    private Sprites sprites;

    @SerializedName("types")
    @Expose
    private List<Types> types;

    @SerializedName("stats")
    @Expose
    private List<Stats> stats;

    public String getName() {
        return name;
    }

    public int getId() {return id;}

    public List<Types> getTypes() {
        return types;
    }

    public Sprites getSprites() {return sprites;}

    public List<Stats> getStats() {return stats;}
}
