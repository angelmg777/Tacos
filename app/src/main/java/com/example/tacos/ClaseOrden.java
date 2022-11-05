package com.example.tacos;

public class ClaseOrden {
    public ClaseTaco[] platillos;
    public ClaseBebida[] bebidas;

    public ClaseOrden() {
        this.platillos = null;
        this.bebidas = null;
    }

    public ClaseOrden(ClaseTaco[] platillos, ClaseBebida[] bebidas) {
        this.platillos = platillos;
        this.bebidas = bebidas;
    }

    public ClaseTaco[] getPlatillos() {
        return platillos;
    }

    public ClaseBebida[] getBebidas() {
        return bebidas;
    }

    public void setPlatillos(ClaseTaco[] platillos) {
        this.platillos = platillos;
    }

    public void setBebidas(ClaseBebida[] bebidas) {
        this.bebidas = bebidas;
    }
}
