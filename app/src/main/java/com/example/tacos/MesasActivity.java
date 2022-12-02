package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MesasActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    Button btVolverMesas;
    ListView LVMesas;
    String[] elementos;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);

        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        LVMesas =  (ListView) findViewById(R.id.listViewMesas);
        btVolverMesas = (Button) findViewById(R.id.btnVolverMesas);

       String[] mesas = {"Mesa 1", "Mesa 2", "Mesa 3", "Mesa 4", "Mesa 5", "Mesa 6", "Mesa 7", "Mesa 8", "Mesa 9", "Mesa 10", "Mesa 11", "Mesa 12"};


        //Ponemos el adaptador (para que se muestren los elementos en el listview)
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,mesas);
        LVMesas.setAdapter(adapter);


        //Hacemos el efecto chido de borrar con click
        LVMesas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {

                //Mostramos una alerta
                new AlertDialog.Builder(MesasActivity.this)
                        .setTitle("¿Desea revisar las ordenes de la mesa "+(item+1)+"?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                entrarMesa(item);

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();

                return false;
            }

        });//LongClickListener
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


    public void entrarMesa(int item){
        Intent intent = new Intent(this, MesaTotalActivity.class);
        //Nos llevamos todos de paseo
        intent.putExtra("listaTacos", listaTacos);
        intent.putExtra("listaBebidas", listaBebidas);
        intent.putExtra("listaOrdenes", listaOrdenes);
        intent.putExtra("arrayMesas", arrayMesas);
        intent.putExtra("item", item);
        startActivity(intent);
        this.finish();
    }
}