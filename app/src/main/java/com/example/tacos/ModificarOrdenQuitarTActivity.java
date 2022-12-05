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

public class ModificarOrdenQuitarTActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    String ordenId;
    Button btnVolverModificarOrdenQuitarT;
    ListView lvModificarQuitarT;
    String[] elementos;
    ArrayAdapter<String> adapter;
    ClaseOrden ordenTemporal;
    int idInt, posicionDelTemporal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_orden_quitar_tactivity);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta activity
        ordenId = (String) getIntent().getStringExtra("ordenId");
        lvModificarQuitarT = (ListView) findViewById(R.id.lvModificarQuitarT);
        btnVolverModificarOrdenQuitarT = (Button) findViewById(R.id.btnVolverModificarOrdenQuitarT);
        idInt = Integer.parseInt(ordenId); //Aqui lo pasamos a integer

        //Hacemos un for para buscar el elemento que comparta el id de ordenId y asignarlo a la variable temporal
        for (int i = 0; i < listaOrdenes.size(); i++) {
            if(listaOrdenes.get(i).getId() == idInt){
                ordenTemporal = listaOrdenes.get(i);
                posicionDelTemporal = i;
                i = listaOrdenes.size();
            }
        }

        if(ordenTemporal.getPlatillos().isEmpty()){
            volver();
            return;
        }

        //Creo un array String donde junto el nombre del taco y su precio para que se vea mas presentable
        elementos = new String[ordenTemporal.getPlatillos().size()];

        for (int i = 0; i < elementos.length; i++) {
            String precio = Integer.toString(ordenTemporal.getPlatillos().get(i).getPrecio());
            elementos[i] = ordenTemporal.getPlatillos().get(i).getNombre() + " $" + precio;
        }

        //Ponemos el adaptador (para que se muestren los elementos en el listview)
        adapter = new ArrayAdapter<String>(this, R.layout.listperzonalizada, elementos);
        lvModificarQuitarT.setAdapter(adapter);

        //Hacemos el efecto chido de borrar con click
        lvModificarQuitarT.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {

                //Mostramos una alerta
                new AlertDialog.Builder(ModificarOrdenQuitarTActivity.this)
                        .setTitle("¿Remover " + ordenTemporal.getPlatillos().get(item).getNombre() + " de la orden con el ID " + ordenId + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Se ha removido " + ordenTemporal.getPlatillos().get(item).getNombre() + " de la orden con el ID " + ordenId, Toast.LENGTH_SHORT);
                                toast.show();

                                listaOrdenes.get(posicionDelTemporal).getPlatillos().remove(item);

                                //El codigo de aqui es para tambien modificar los cambios en su respectiva mesa
                                ClaseOrden temporal = listaOrdenes.get(posicionDelTemporal);
                                int id = temporal.getMesaId();

                                for (int j = 0; j < arrayMesas[id].getCuentas().size(); j++) {
                                    //Que si son la misma cuenta dentro de la mesa, se modifique
                                    if(arrayMesas[id].getCuentas().get(j).getId() == temporal.getId()){
                                        arrayMesas[id].getCuentas().get(j).setPlatillos(temporal.getPlatillos());
                                    }
                                }

                                recargar();

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

    public void volver(){
        Intent volver = new Intent(this, ModificarOrdenActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        startActivity(volver);
        this.finish();
    }

    //Recargamos la pantalla
    public void recargar(){
        Intent elTaco = new Intent(this, ModificarOrdenQuitarTActivity.class);
        //Nos llevamos todos de paseo
        elTaco.putExtra("listaTacos", listaTacos);
        elTaco.putExtra("listaBebidas", listaBebidas);
        elTaco.putExtra("listaOrdenes", listaOrdenes);
        elTaco.putExtra("arrayMesas", arrayMesas);
        elTaco.putExtra("ordenId",ordenId);
        startActivity(elTaco);
        this.finish();
    }

}