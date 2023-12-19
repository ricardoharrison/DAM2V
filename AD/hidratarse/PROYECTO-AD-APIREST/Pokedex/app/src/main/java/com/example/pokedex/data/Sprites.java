package com.example.pokedex.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sprites {
    @SerializedName("front_default")
    @Expose
    private String front_default;

    @SerializedName("back_default")
    @Expose
    private String back_default;

    public String getFrontDefault() {
        return front_default;
    }

    public String getBack_default() {return back_default;}
}