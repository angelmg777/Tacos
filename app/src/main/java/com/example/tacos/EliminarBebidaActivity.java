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

public class EliminarBebidaActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_eliminar_bebida);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta Activity
        lvLista = (ListView) findViewById(R.id.lvListaBebidas);
        btnVolver = (Button) findViewById(R.id.btnVolverElBebidas);
        btnRecargar = (Button) findViewById(R.id.btnRecargarBebidas);

        //Creo un array String donde junto el nombre del taco y su precio para que se vea mas presentable
        elementos = new String[listaBebidas.size()];

        for (int i = 0; i < elementos.length; i++) {
            String precio = Integer.toString(listaBebidas.get(i).getPrecio());
            elementos[i] = listaBebidas.get(i).getNombre() + " $" + precio;
        }

        //Ponemos el adaptador (para que se muestren los elementos en el listview)
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, elementos);
        lvLista.setAdapter(adapter);

        //Hacemos el efecto chido de borrar con click
        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {

                //Mostramos una alerta
                new AlertDialog.Builder(EliminarBebidaActivity.this)
                        .setTitle("¿Remover " + listaBebidas.get(item).getNombre() + " del menu?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Se ha removido " + listaBebidas.get(item).getNombre() + " del menu, por favor, recargue la pantalla", Toast.LENGTH_SHORT);
                                toast.show();

                                listaBebidas.remove(item);
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
        Intent elBebida = new Intent(this, EliminarBebidaActivity.class);
        //Nos llevamos todos de paseo
        elBebida.putExtra("listaTacos", listaTacos);
        elBebida.putExtra("listaBebidas", listaBebidas);
        elBebida.putExtra("listaOrdenes", listaOrdenes);
        elBebida.putExtra("arrayMesas", arrayMesas);
        startActivity(elBebida);
        this.finish();
    }

}