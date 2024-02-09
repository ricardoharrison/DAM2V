package com.rittz.apipokemon;

public class Pokemon {
    int pokedexNumber;
    String name;
    String type1;
    String type2;
    String sprite;

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokedexNumber= " + pokedexNumber +
                ", name= '" + name + '\'' +
                ", type1= '" + type1 + '\'' +
                ", type2= '" + type2 + '\'' +
                ", sprite= '" + sprite + '\'' +
                '}';
    }
}
