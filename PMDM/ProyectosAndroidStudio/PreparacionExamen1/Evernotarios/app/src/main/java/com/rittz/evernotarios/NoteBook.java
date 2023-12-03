package com.rittz.evernotarios;

import java.io.Serializable;
import java.util.ArrayList;

public class NoteBook implements Serializable {
    ArrayList<Note> notebook = new ArrayList<>();

    public void addNote(Note note){
        notebook.add(note);
    }

    @Override
    public String toString() {
        String msg = "";
        for(Note note : notebook){
            msg += "--------------------\n" + note + "\n";
        }
        return msg;
    }
}
