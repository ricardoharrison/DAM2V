package com.rittz.evernotarios;

import java.io.Serializable;
import java.util.Calendar;

public class Note implements Serializable {
    String title;
    String msg;
    Calendar date;

    public Note(String title, String msg, Calendar date) {
        this.title = title;
        this.msg = msg;
        this.date = date;
    }

    @Override
    public String toString() {
        return title + "\n\n\t" + msg + "\n\nNota enviada el " +
                date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR) +
                " a las " + date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE);
    }
}
