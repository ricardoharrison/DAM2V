package com.example.thelordoftheringscharacterselector;

public enum LOTRCharacters {
    FRODO (false), GANDALF(false), LEGOLAS(false);

    private boolean isSelected;

    LOTRCharacters (boolean isSelected){
        this.isSelected = isSelected;
    }

    public boolean isSelected(){
        return isSelected;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }
}
