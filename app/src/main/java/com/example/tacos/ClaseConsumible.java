package com.example.tacos;

import java.io.Serializable;

public abstract class ClaseConsumible implements Serializable {

    public int precio;
    public String nombre;

    public ClaseConsumible(){
        precio = 0;
        nombre = "";
    }

    public ClaseConsumible(String n, int p) {
        precio = p;
        nombre = n;
    }

    public void setPrecio(int p) {
        precio = p;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

}
