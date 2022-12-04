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

public class ModificarOrdenAnadirBActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    String ordenId;
    ListView lvModificarAñadirB;
    Button btnVolverModificarAñadirB;
    String[] elementos;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_orden_anadir_bactivity);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta activity
        ordenId = (String) getIntent().getStringExtra("ordenId");
        lvModificarAñadirB = (ListView) findViewById(R.id.lvModificarAñadirB);
        btnVolverModificarAñadirB = (Button) findViewById(R.id.btnVolverModificarOrdenAñadirB);

        //Creo un array String donde junto el nombre del taco y su precio para que se vea mas presentable
        elementos = new String[listaBebidas.size()];

        for (int i = 0; i < elementos.length; i++) {
            String precio = Integer.toString(listaBebidas.get(i).getPrecio());
            elementos[i] = listaBebidas.get(i).getNombre() + " $" + precio;
        }

        //Ponemos el adaptador (para que se muestren los elementos en el listview)
        adapter = new ArrayAdapter<String>(this, R.layout.listperzonalizada, elementos);
        lvModificarAñadirB.setAdapter(adapter);

        //Hacemos el efecto chido de borrar con click
        lvModificarAñadirB.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {

                //Mostramos una alerta
                new AlertDialog.Builder(ModificarOrdenAnadirBActivity.this)
                        .setTitle("¿Añadir " + listaBebidas.get(item).getNombre() + " a la orden con el ID " + ordenId + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Se ha añadido " + listaBebidas.get(item).getNombre() + " a la orden con el ID " + ordenId, Toast.LENGTH_SHORT);
                                toast.show();

                                //Pasamos ordenId de String a Integer para poder usarlo para buscar
                                int idInt = Integer.parseInt(ordenId);

                                for(int it=0; it<listaOrdenes.size() ;it++){

                                    //Esto nomas para encontrar la orden con el id que nos paso la activity ModificarOrden,
                                    // y meterle el platillo
                                    if(listaOrdenes.get(it).getId() == idInt){
                                        listaOrdenes.get(it).getBebidas().add(listaBebidas.get(item));
                                        //Esto para que se acabe el for
                                        it=listaOrdenes.size();
                                    }

                                }

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

    }//onCreate

    public void volver(View view){
        Intent volver = new Intent(this, ModificarOrdenActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        startActivity(volver);
        this.finish();
    }

}