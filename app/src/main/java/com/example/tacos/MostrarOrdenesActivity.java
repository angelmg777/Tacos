package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MostrarOrdenesActivity extends AppCompatActivity {

    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ordenes);

        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        init();
    }

    public void init(){
        ListAdapter list = new ListAdapter(listaOrdenes, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ClaseOrden item) {


                entrarOrden(item);

            }
        });
        RecyclerView recycle = findViewById(R.id.recyclerViewOrdenes);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(list);
    }

    public void entrarOrden(ClaseOrden item){
        Intent intent = new Intent(this, OrdenDetalleActivity.class);
        //Nos llevamos todos de paseo
        intent.putExtra("listaTacos", listaTacos);
        intent.putExtra("listaBebidas", listaBebidas);
        intent.putExtra("listaOrdenes", listaOrdenes);
        intent.putExtra("arrayMesas", arrayMesas);
        intent.putExtra("item", item);
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