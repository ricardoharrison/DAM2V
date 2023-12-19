package com.example.pokedex.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Types {
    public Type getType() {
        return type;
    }
    @SerializedName("type")
    @Expose
    Type type;
}
