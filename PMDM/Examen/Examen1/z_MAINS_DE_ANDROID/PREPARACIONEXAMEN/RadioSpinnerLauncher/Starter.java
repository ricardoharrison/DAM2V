package com.rittz.radiospinnerlauncher;

import java.io.Serializable;

public class Starter implements Serializable {
    CharSequence name;
    PokemonStarterType mainType;
    SecondaryPokemonType secondaryType;
    int color;

    public Starter(CharSequence name, PokemonStarterType mainType, SecondaryPokemonType secondaryType, int color) {
        this.name = name;
        this.mainType = mainType;
        this.secondaryType = secondaryType;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Starter{" +
                "name=" + name +
                ", mainType=" + mainType +
                ", secondaryType=" + secondaryType +
                ", color=" + color +
                '}';
    }
}
