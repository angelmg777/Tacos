package com.example.tacos;

import java.io.Serializable;

public class ClaseTaco implements Serializable {
    public int precio;
    public String nombre;

    public ClaseTaco(){
        this.precio = 0;
        this.nombre = "";
    }

    public ClaseTaco(String nombre, int precio) {
        this.precio = precio;
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }
}
