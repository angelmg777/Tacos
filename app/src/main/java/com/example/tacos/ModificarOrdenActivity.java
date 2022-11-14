package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class ModificarOrdenActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    Button btnModificarAñadirT, btnModificarAñadirB, btnModificarQuitarT, btnModificarQuitarB;
    Spinner spnModificarOrdenId;
    String[] arrayIds;
    String ordenId;

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
        btnModificarAñadirT = (Button) findViewById(R.id.btnModificarAñadirT);
        btnModificarAñadirB = (Button) findViewById(R.id.btnModificarAñadirB);
        btnModificarQuitarT = (Button) findViewById(R.id.btnModificarQuitarT);
        btnModificarQuitarB = (Button) findViewById(R.id.btnModificarQuitarB);
        spnModificarOrdenId = (Spinner) findViewById(R.id.spnModificarOrdenId);

        arrayIds = new String[listaOrdenes.size()];
        //Creamos el array para el spinner
        for(int i = 0; i < listaOrdenes.size(); i++){
            int idNum = listaOrdenes.get(i).getId();
            String idString = Integer.toString(idNum);
            arrayIds[i] = idString;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayIds);
        spnModificarOrdenId.setAdapter(adapter);
    }

    public void volver(View view){
        Intent volver = new Intent(this, AdministrarOrdenActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        startActivity(volver);
        this.finish();
    }

    public void modificarOrdenAñadirT(View view){
        ordenId = spnModificarOrdenId.getSelectedItem().toString();

        Intent volver = new Intent(this, ModificarOrdenAnadirTActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        volver.putExtra("ordenId", ordenId);
        startActivity(volver);
        this.finish();
    }

    public void modificarOrdenQuitarT(View view){
        ordenId = spnModificarOrdenId.getSelectedItem().toString();

        /*Intent volver = new Intent(this, ModificarOrdenQuitarTActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        volver.putExtra("ordenId", ordenId);
        startActivity(volver);
        this.finish();*/
    }

    public void modificarOrdenAñadirB(View view){
        ordenId = spnModificarOrdenId.getSelectedItem().toString();

        /*Intent volver = new Intent(this, ModificarOrdenAñadirBActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        volver.putExtra("ordenId", ordenId);
        startActivity(volver);
        this.finish();*/
    }

    public void modificarOrdenQuitarB(View view){
        ordenId = spnModificarOrdenId.getSelectedItem().toString();

        /*Intent volver = new Intent(this, ModificarOrdenQuitarBActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        volver.putExtra("ordenId", ordenId);
        startActivity(volver);
        this.finish();*/
    }

    public void eliminarOrden(View view){

    }

}