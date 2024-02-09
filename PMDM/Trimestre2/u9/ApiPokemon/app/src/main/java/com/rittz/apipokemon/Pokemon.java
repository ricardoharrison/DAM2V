package com.rittz.apipokemon;

import java.util.ArrayList;

public class Pokemon {
    String name;
    int id;
    Sprites sprites;
    Types[] types;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Sprites getSprites() {
        return sprites;
    }

    @Override
    public String toString() {
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        return name + "\nPokedex: " + id + "\nTypes: " + types[0].type.name + (types.length > 1 ? "/" + types[1].type.name : "");
    }
}