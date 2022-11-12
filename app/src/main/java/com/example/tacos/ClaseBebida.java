package com.example.tacos;

import java.io.Serializable;

public class ClaseBebida extends ClaseConsumible implements Serializable {

    public ClaseBebida() {
        this.precio = 0;
        this.nombre = "";
    }

    public ClaseBebida(String n, int p) {
        super(n, p);
    }

}
