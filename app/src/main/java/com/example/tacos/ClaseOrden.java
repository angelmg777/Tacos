package com.example.tacos;

import java.io.Serializable;
import java.util.ArrayList;

public class ClaseOrden implements Serializable {
    public int id, mesaId;
    public String color;
    public ArrayList<ClaseTaco> platillos;
    public ArrayList<ClaseBebida> bebidas;

    public ClaseOrden() {
        this.platillos = null;
        this.bebidas = null;
        id=0;
    }

    public ClaseOrden(ArrayList<ClaseTaco> platillos, ArrayList<ClaseBebida> bebidas) {
        this.platillos = platillos;
        this.bebidas = bebidas;
        id=0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMesaId() {
        return mesaId;
    }

    public void setMesaId(int mesaId) {
        this.mesaId = mesaId;
    }
}
