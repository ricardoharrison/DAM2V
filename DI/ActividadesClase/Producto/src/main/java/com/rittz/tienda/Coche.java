/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rittz.tienda;

import com.google.gson.Gson;

/**
 *
 * @author at1DAM2
 */
public class Coche {
    
    int id, potencia, autonomia, precio, maletero;
    String modelo;

    public Coche(String modelo, int potencia, int autonomia, int precio, int maletero) {
        this.modelo = modelo;
        this.potencia = potencia;
        this.autonomia = autonomia;
        this.precio = precio;
        this.maletero = maletero;        
    } 
    
    public int getPotencia() {
        return potencia;
    }

    public int getAutonomia() {
        return autonomia;
    }

    public int getPrecio() {
        return precio;
    }

    public int getMaletero() {
        return maletero;
    }

    public String getModelo() {
        return modelo;
    }
    
    @Override
    public String toString() {
        return "Coche{" + "id=" + id + ", potencia=" + potencia + ", autonomia=" + autonomia + ", precio=" + precio + ", maletero=" + maletero + ", modelo=" + modelo + '}';
    }
    
    public String toCsvLine() {
        return modelo + "," + potencia + "," + autonomia + "," + precio + "," + maletero + "\n";
    }
    
    public String toJsonFile() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
    
}
