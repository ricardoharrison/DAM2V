package com.rittz.apipokemon;

import java.util.List;

public class Pokemon {
    String name;
    int id;
    List<String> sprites;

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
