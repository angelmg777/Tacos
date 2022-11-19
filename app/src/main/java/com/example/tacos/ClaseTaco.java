package com.example.tacos;

import java.io.Serializable;

public class ClaseTaco extends ClaseConsumible implements Serializable{

    public ClaseTaco() {
        this.precio = 0;
        this.nombre = "";
    }

    public ClaseTaco(String nombre, int precio) {
        super(nombre, precio);
    }


}
