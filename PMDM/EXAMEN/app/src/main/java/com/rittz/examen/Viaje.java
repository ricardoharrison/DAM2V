package com.rittz.examen;

import java.io.Serializable;
import java.util.Calendar;

public class Viaje implements Serializable {
    String origen;
    String destino;
    Calendar fechaIda;
    Calendar fechaVuelta;

    public Viaje(String origen, String destino, Calendar fechaIda, Calendar fechaVuelta) {
        this.origen = origen;
        this.destino = destino;
        this.fechaIda = fechaIda;
        this.fechaVuelta = fechaVuelta;
    }

    @Override
    public String toString() {
        String mensaje = "";
        if(fechaVuelta == null){
            mensaje = origen + '-' + destino + '\n' + "Sólo ida\n" + fechaIda.get(Calendar.DAY_OF_MONTH) + "/" + (fechaIda.get(Calendar.MONTH) + 1) + "/" + fechaIda.get(Calendar.YEAR);
        } else {
            mensaje = origen + '-' + destino + '\n' + "Ida:\n" + fechaIda.get(Calendar.DAY_OF_MONTH) + "/" + (fechaIda.get(Calendar.MONTH) + 1) + "/" + fechaIda.get(Calendar.YEAR) +
                        "\nRegreso:\n" + fechaVuelta.get(Calendar.DAY_OF_MONTH) + "/" + (fechaVuelta.get(Calendar.MONTH) + 1) + "/" + fechaVuelta.get(Calendar.YEAR);

        }
        return mensaje;
    }
}
