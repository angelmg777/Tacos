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
    int item,ID;

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

        ID = element.getId();


        String res = "";


        for (int x=0; x< listaOrdenes.size(); x++){
            int z = listaOrdenes.get(x).getId();
            if(z==ID) {
                for (int j = 0; j < listaOrdenes.get(x).getPlatillos().size(); j++) {
                    res += (listaOrdenes.get(x).getPlatillos().get(j).getNombre() + " $" + listaOrdenes.get(x).getPlatillos().get(j).getPrecio() + "\n");
                }
                for (int j = 0; j < listaOrdenes.get(x).getBebidas().size(); j++) {
                    res += (listaOrdenes.get(x).getBebidas().get(j).getNombre() + " $" + listaOrdenes.get(x).getBebidas().get(j).getPrecio() + "\n");
                }

                res += "\nPerteneciente a la mesa: " + (listaOrdenes.get(x).getMesaId()+1);
            }
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

                        for (int x=0; x< listaOrdenes.size(); x++){
                                    int z =listaOrdenes.get(x).getId();
                                    if(z==ID){

                                            listaOrdenes.remove(x);
                                        Toast toast = Toast.makeText(getApplicationContext(), "Orden Realizada", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }


                        }



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
