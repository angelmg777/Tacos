package com.example.tacos;

import java.io.Serializable;
import java.util.ArrayList;

public class ClaseOrden implements Serializable {
    public ArrayList<ClaseTaco> platillos;
    public ArrayList<ClaseBebida> bebidas;

    public ClaseOrden() {
        this.platillos = null;
        this.bebidas = null;
    }

    public ClaseOrden(ArrayList<ClaseTaco> platillos, ArrayList<ClaseBebida> bebidas) {
        this.platillos = platillos;
        this.bebidas = bebidas;
    }

    public ArrayList<ClaseTaco> getPlatillos() {
        return platillos;
    }

    public ArrayList<ClaseBebida> getBebidas() {
        return bebidas;
    }

    public void setPlatillos(ArrayList<ClaseTaco> platillos) {
        this.platillos = platillos;
    }

    public void setBebidas(ArrayList<ClaseBebida> bebidas) {
        this.bebidas = bebidas;
    }
}
