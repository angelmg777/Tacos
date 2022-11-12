package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class AdministrarOrdenActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    ImageButton imbNuevaOrden, imbModificarOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_orden);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta activity
        imbNuevaOrden = (ImageButton) findViewById(R.id.imbNuevaOrden);
        imbModificarOrden = (ImageButton) findViewById(R.id.imbModificarOrden);
    }

    public void nuevaOrden(View view){
        Intent intent = new Intent(this, NuevaOrdenTacosActivity.class);
        //Nos llevamos todos de paseo
        intent.putExtra("listaTacos", listaTacos);
        intent.putExtra("listaBebidas", listaBebidas);
        intent.putExtra("listaOrdenes", listaOrdenes);
        intent.putExtra("arrayMesas", arrayMesas);
        startActivity(intent);
        this.finish();
    }

    public void modificarOrden(View view){
        if(listaOrdenes.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "¡No hay ordenes para modificar!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        Intent intent = new Intent(this, ModificarOrdenActivity.class);
        //Nos llevamos todos de paseo
        intent.putExtra("listaTacos", listaTacos);
        intent.putExtra("listaBebidas", listaBebidas);
        intent.putExtra("listaOrdenes", listaOrdenes);
        intent.putExtra("arrayMesas", arrayMesas);
        startActivity(intent);
        this.finish();
    }

    public void volver(View view){
        Intent volver = new Intent(this, MenuActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        startActivity(volver);
        this.finish();
    }

}