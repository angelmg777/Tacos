package com.example.tacos;

public class ClaseUsuario {
    String correo, contraseña;
    boolean registrado;

    ClaseUsuario(){
        correo = "";
        contraseña = "";
        registrado = false;
    }

    public ClaseUsuario(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.registrado = true;

    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isRegistrado() {
        return registrado;
    }

    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }


}
