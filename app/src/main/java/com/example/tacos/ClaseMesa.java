package com.example.tacos;

public class ClaseMesa {
    public int id;
    public ClaseOrden[] cuentas;

    public ClaseMesa() {
        this.id = 0;
        this.cuentas = null;
    }

    public ClaseMesa(int id, ClaseOrden[] cuentas) {
        this.id = id;
        this.cuentas = cuentas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCuentas(ClaseOrden[] cuentas) {
        this.cuentas = cuentas;
    }

    public int getId() {
        return id;
    }

    public ClaseOrden[] getCuentas() {
        return cuentas;
    }
}
