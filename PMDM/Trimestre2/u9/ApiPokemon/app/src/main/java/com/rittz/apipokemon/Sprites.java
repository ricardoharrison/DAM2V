package com.rittz.apipokemon;

public class Sprites {
    private String front_default; // literal como aparece en la api

    public String getFront_default() {
        return front_default;
    }

    // Constructor, getters, and setters
    @Override
    public String toString() {
        return "Sprites{" +
                "frontDefault='" + front_default + '\'' +
                '}';
    }

}
