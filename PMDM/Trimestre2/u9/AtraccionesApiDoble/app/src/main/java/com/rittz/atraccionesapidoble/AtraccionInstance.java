package com.rittz.atraccionesapidoble;

import java.util.List;

public class AtraccionInstance {
    String nombre;
    String descripcion;
    int ocupantes;

    List<Comentarios> comentarios;

    @Override
    public String toString() {
        return "AtraccionInstance{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ocupantes=" + ocupantes +
                ", comentarios=" + comentarios +
                '}';
    }
}
