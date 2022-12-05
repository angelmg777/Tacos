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
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NuevaOrdenBebidasActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    ArrayList<ClaseTaco> ordenTacos;
    ArrayList<ClaseBebida> ordenBebidas = new ArrayList<ClaseBebida>();
    String[] elementos;
    Button btnCancelarNuevaOrdenBebidas, btnAgregarNuevaOrdenBebidas;
    ListView lvAgregarBebida;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_orden_bebidas);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta activity
        ordenTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("ordenTacos");
        btnAgregarNuevaOrdenBebidas = (Button) findViewById(R.id.btnAgregarNuevaOrdenBebidas);
        btnCancelarNuevaOrdenBebidas = (Button) findViewById(R.id.btnCancelarNuevaOrdenBebidas);
        lvAgregarBebida = (ListView) findViewById(R.id.lvAgregarBebida);

        //Creo un array String donde junto el nombre del taco y su precio para que se vea mas presentable
        elementos = new String[listaBebidas.size()];

        for (int i = 0; i < elementos.length; i++) {
            String precio = Integer.toString(listaBebidas.get(i).getPrecio());
            elementos[i] = listaBebidas.get(i).getNombre() + " $" + precio;
        }

        //Ponemos el adaptador (para que se muestren los elementos en el listview)
        adapter = new ArrayAdapter<String>(this, R.layout.listperzonalizada, elementos);
        lvAgregarBebida.setAdapter(adapter);

        //Hacemos el efecto chido de borrar con click
        lvAgregarBebida.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {

                //Mostramos una alerta
                new AlertDialog.Builder(NuevaOrdenBebidasActivity.this)
                        .setTitle("¿Agregar " + listaBebidas.get(item).getNombre() + " a la orden?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                ClaseBebida objetoBebida = listaBebidas.get(item);
                                ordenBebidas.add(objetoBebida);

                                Toast toast = Toast.makeText(getApplicationContext(), "Se ha agregado " + listaBebidas.get(item).getNombre() + " a la orden, van " + ordenBebidas.size() + " bebidas", Toast.LENGTH_SHORT);
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

    } //onCreate

    public void cancelar(View view){
        //Mostramos una alerta
        new AlertDialog.Builder(NuevaOrdenBebidasActivity.this)
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

    //Creamos un alert con un spinner para la seleccion de la mesa
    public void agregarOrden(View view){
        ClaseOrden nuevaOrden = new ClaseOrden(ordenTacos,ordenBebidas);

        //Checamos que las listas de la nueva orden no esten vacias
        if(nuevaOrden.getPlatillos().isEmpty() && nuevaOrden.getBebidas().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "No puede crear una orden vacia", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } //if

        //Creamos la alert con el spinner
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(NuevaOrdenBebidasActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialogo_spinner, null);
        mBuilder.setTitle("¿A que mesa corresponde la orden?");
        Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinnerDialogo);

        String[] numeroMesas = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        adapter = new ArrayAdapter<String>(NuevaOrdenBebidasActivity.this,
                android.R.layout.simple_spinner_item,
                numeroMesas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //Creamos el id de la nueva orden:
                int nuevoId;
                if(listaOrdenes.isEmpty()){
                    nuevoId = 1;
                }else{
                    ClaseOrden temporal = listaOrdenes.get(listaOrdenes.size()-1);
                    nuevoId = temporal.getId() + 1;
                }
                nuevaOrden.setId(nuevoId);

                //Lo añadimos a la lista de ordenes
                listaOrdenes.add(nuevaOrden);

                //Obtenemos el id de la mesa y guardamos la orden dentro de la respectiva mesa
                String s = mSpinner.getSelectedItem().toString();
                int mesaId = Integer.parseInt(s);
                nuevaOrden.setMesaId(mesaId-1);
                arrayMesas[mesaId-1].cuentas.add(nuevaOrden);



                Toast.makeText(NuevaOrdenBebidasActivity.this, "Añadida la orden numero " + arrayMesas[mesaId-1].cuentas.size() + " de la mesa " + s + ", con el ID de " + nuevaOrden.getId(), Toast.LENGTH_SHORT).show();

                dialogInterface.dismiss();

                volver();
            }
        });

        mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

    }//agregarOrden


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