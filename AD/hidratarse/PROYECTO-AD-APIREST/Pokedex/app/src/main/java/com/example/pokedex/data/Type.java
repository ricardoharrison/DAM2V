package com.example.pokedex.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {
    public String getName() {
        return name;
    }

    @SerializedName("name")
    @Expose
    private String name;



}
