package com.rittz.atraccionesapidoble;

import java.util.List;

public class Crypto {
    String nombre;
    Float euros;

    List<Estafado> estafados;

    public String getNombre() {
        return nombre;
    }

    public Float getEuros() {
        return euros;
    }

    public List<Estafado> getEstafados() {
        return estafados;
    }
}
