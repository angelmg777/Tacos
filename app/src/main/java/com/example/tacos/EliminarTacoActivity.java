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

public class EliminarTacoActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    ListView lvLista;
    Button btnVolver, btnRecargar;
    String[] elementos;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_taco);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta Activity
        lvLista = (ListView) findViewById(R.id.lvListaTacos);
        btnVolver = (Button) findViewById(R.id.btnVolverElTaco);
        btnRecargar = (Button) findViewById(R.id.btnRecargarTacos);

        //Creo un array String donde junto el nombre del taco y su precio para que se vea mas presentable
        elementos = new String[listaTacos.size()];

        for (int i = 0; i < elementos.length; i++) {
            String precio = Integer.toString(listaTacos.get(i).getPrecio());
            elementos[i] = listaTacos.get(i).getNombre() + " $" + precio;
        }

        //Ponemos el adaptador (para que se muestren los elementos en el listview)
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, elementos);
        lvLista.setAdapter(adapter);

        //Hacemos el efecto chido de borrar con click
        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {

                //Mostramos una alerta
                new AlertDialog.Builder(EliminarTacoActivity.this)
                        .setTitle("¿Remover " + listaTacos.get(item).getNombre() + " del menu?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Se ha removido " + listaTacos.get(item).getNombre() + " del menu, por favor, recargue la pantalla", Toast.LENGTH_SHORT);
                                toast.show();

                                listaTacos.remove(item);
                                adapter.notifyDataSetChanged();

                                lvLista.setEnabled(false);

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();

                return false;
            }

        });

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

    //Recargamos la pantalla
    public void recargar(View view){
        Intent elTaco = new Intent(this, EliminarTacoActivity.class);
        //Nos llevamos todos de paseo
        elTaco.putExtra("listaTacos", listaTacos);
        elTaco.putExtra("listaBebidas", listaBebidas);
        elTaco.putExtra("listaOrdenes", listaOrdenes);
        elTaco.putExtra("arrayMesas", arrayMesas);
        startActivity(elTaco);
        this.finish();
    }

}