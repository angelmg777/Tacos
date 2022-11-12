package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class ModificarOrdenActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_orden);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta activity

    }
}