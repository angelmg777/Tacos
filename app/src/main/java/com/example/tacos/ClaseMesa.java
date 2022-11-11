package com.example.tacos;

import java.io.Serializable;
import java.util.ArrayList;

public class ClaseMesa implements Serializable {
    public int id;
    public ArrayList<ClaseOrden> cuentas;

    public ClaseMesa() {
        this.id = 0;
        this.cuentas = new ArrayList<ClaseOrden>();
    }

    public ClaseMesa(int id, ArrayList<ClaseOrden> cuentas) {
        this.id = id;
        this.cuentas = cuentas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCuentas(ArrayList<ClaseOrden> cuentas) {
        this.cuentas = cuentas;
    }

    public int getId() {
        return id;
    }

    public ArrayList<ClaseOrden> getCuentas() {
        return cuentas;
    }
}
