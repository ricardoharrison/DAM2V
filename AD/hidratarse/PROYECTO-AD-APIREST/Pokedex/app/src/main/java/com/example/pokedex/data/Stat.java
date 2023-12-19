package com.example.pokedex.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {
    @SerializedName("name")
    @Expose
    String name;

    public String getName() {
        return name;
    }
}