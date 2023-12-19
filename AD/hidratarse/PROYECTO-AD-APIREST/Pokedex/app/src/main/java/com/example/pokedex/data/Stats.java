package com.example.pokedex.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("stat")
    @Expose
    Stat stat;

    @SerializedName("base_stat")
    @Expose
    int baseStat;

    public Stat getStat() {
        return stat;
    }

    public int getBaseStat() {
        return baseStat;
    }
}
