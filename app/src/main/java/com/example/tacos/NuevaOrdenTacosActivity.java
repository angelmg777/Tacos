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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NuevaOrdenTacosActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    ArrayList<ClaseTaco> ordenTacos = new ArrayList<ClaseTaco>();
    String[] elementos;
    Button btnCancelarNuevaOrdenTacos, btnAgregarNuevaOrdenTacos;
    ListView lvAgregarTaco;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_orden_tacos);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta activity
        btnAgregarNuevaOrdenTacos = (Button) findViewById(R.id.btnAgregarNuevaOrdenTacos);
        btnCancelarNuevaOrdenTacos = (Button) findViewById(R.id.btnCancelarNuevaOrdenTacos);
        lvAgregarTaco = (ListView) findViewById(R.id.lvAgregarTaco);

        //Creo un array String donde junto el nombre del taco y su precio para que se vea mas presentable
        elementos = new String[listaTacos.size()];

        for (int i = 0; i < elementos.length; i++) {
            String precio = Integer.toString(listaTacos.get(i).getPrecio());
            elementos[i] = listaTacos.get(i).getNombre() + " $" + precio;
        }

        //Ponemos el adaptador (para que se muestren los elementos en el listview)
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, elementos);
        lvAgregarTaco.setAdapter(adapter);

        //Hacemos el efecto chido de borrar con click
        lvAgregarTaco.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {

                //Mostramos una alerta
                new AlertDialog.Builder(NuevaOrdenTacosActivity.this)
                        .setTitle("¿Agregar " + listaTacos.get(item).getNombre() + " a la orden?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                ClaseTaco objetoTaco = listaTacos.get(item);
                                ordenTacos.add(objetoTaco);

                                Toast toast = Toast.makeText(getApplicationContext(), "Se ha agregado " + listaTacos.get(item).getNombre() + " a la orden, van " + ordenTacos.size() + " tacos", Toast.LENGTH_SHORT);
                                toast.show();

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

    public void cancelar(View view){
        //Mostramos una alerta
        new AlertDialog.Builder(NuevaOrdenTacosActivity.this)
                .setTitle("¿Esta seguro de que quiere cancelar la orden?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        volver();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }//cancelar

    //Mandamos all a la nueva orden tacos activity, incluido, lo mas imporante, ordenTacos
    public void agregarTacos(View view){
        Intent intent = new Intent(this, NuevaOrdenBebidasActivity.class);
        //Nos llevamos todos de paseo
        intent.putExtra("listaTacos", listaTacos);
        intent.putExtra("ordenTacos", ordenTacos);
        intent.putExtra("listaBebidas", listaBebidas);
        intent.putExtra("listaOrdenes", listaOrdenes);
        intent.putExtra("arrayMesas", arrayMesas);
        startActivity(intent);
        this.finish();
    }


    //Creamos la clase de volver fuera de la clase cancelar porque sino nos marca error al poner el codigo
    //dentro de la opcion positiva
    public void volver(){
        Intent volver = new Intent(this, AdministrarOrdenActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        startActivity(volver);
        this.finish();
    }

}