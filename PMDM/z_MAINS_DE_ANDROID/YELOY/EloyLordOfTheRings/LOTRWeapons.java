package com.example.thelordoftheringscharacterselector;

public enum LOTRWeapons {
    SWORD(false),
    RING(false),
    BOW(false);

    private boolean isSelected;

    LOTRWeapons(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
