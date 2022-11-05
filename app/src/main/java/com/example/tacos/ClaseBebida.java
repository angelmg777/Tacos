package com.example.tacos;

public class ClaseBebida {

    public int precio;
    public String nombre;

    public ClaseBebida(){
        this.precio = 0;
        this.nombre = "";
    }

    public ClaseBebida(int precio, String nombre) {
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
