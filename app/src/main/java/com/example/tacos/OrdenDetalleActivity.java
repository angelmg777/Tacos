package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrdenDetalleActivity extends AppCompatActivity {

    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;
    ClaseOrden element;
    int item;

    TextView orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_detalle);
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        orden = (TextView) findViewById(R.id.txtDetalleOrdenn);
        element = (ClaseOrden) getIntent().getSerializableExtra("item");

        item = element.getId()-1;




        String res = "";


        for (int j = 0; j < listaOrdenes.get(item).getPlatillos().size(); j++) {

            res += ( listaOrdenes.get(item).getPlatillos().get(j).getNombre() + " $" + listaOrdenes.get(item).getPlatillos().get(j).getPrecio() + "\n");
        }

        for (int j = 0; j < listaOrdenes.get(item).getBebidas().size(); j++) {
            res += ( listaOrdenes.get(item).getBebidas().get(j).getNombre() + " $" + listaOrdenes.get(item).getBebidas().get(j).getPrecio() + "\n");
        }

        orden.setText(res);


    }

    public void volver(View view){
        Intent volver = new Intent(this, MostrarOrdenesActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        startActivity(volver);
        this.finish();
    }

    public void hecha(View view){



        new AlertDialog.Builder(OrdenDetalleActivity.this)
                .setTitle("Ya se realizo esta orden??")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        listaOrdenes.remove(item);

                        Toast toast = Toast.makeText(getApplicationContext(), "Orden Realizada", Toast.LENGTH_SHORT);
                        toast.show();

                        volver(view);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }
}
