package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ModificarOrdenActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    Button btnModificarAñadirT, btnModificarAñadirB, btnModificarQuitarT, btnModificarQuitarB;
    Spinner spnModificarOrdenId;
    String[] arrayIds;
    String ordenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_orden);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta activity
        btnModificarAñadirT = (Button) findViewById(R.id.btnModificarAñadirT);
        btnModificarAñadirB = (Button) findViewById(R.id.btnModificarAñadirB);
        btnModificarQuitarT = (Button) findViewById(R.id.btnModificarQuitarT);
        btnModificarQuitarB = (Button) findViewById(R.id.btnModificarQuitarB);
        spnModificarOrdenId = (Spinner) findViewById(R.id.spnModificarOrdenId);

        if (listaOrdenes.isEmpty()){
            volver();
            return;
        }

        arrayIds = new String[listaOrdenes.size()];
        //Creamos el array para el spinner
        for(int i = 0; i < listaOrdenes.size(); i++){
            int idNum = listaOrdenes.get(i).getId();
            String idString = Integer.toString(idNum);
            arrayIds[i] = idString;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayIds);
        spnModificarOrdenId.setAdapter(adapter);
    } //onCreate

    public void volver(View view){
        Intent volver = new Intent(this, AdministrarOrdenActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        startActivity(volver);
        this.finish();
    }

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

    public void modificarOrdenAñadirT(View view){
        ordenId = spnModificarOrdenId.getSelectedItem().toString();

        Intent volver = new Intent(this, ModificarOrdenAnadirTActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        volver.putExtra("ordenId", ordenId);
        startActivity(volver);
        this.finish();
    }

    public void modificarOrdenQuitarT(View view){
        ordenId = spnModificarOrdenId.getSelectedItem().toString();
        int intId = Integer.parseInt(ordenId);
        ClaseOrden ordenTemporal = new ClaseOrden();

        for (int i = 0; i < listaOrdenes.size(); i++) {

            if (listaOrdenes.get(i).getId() == intId){
                ordenTemporal = listaOrdenes.get(i);
                i=listaOrdenes.size();
            }

        }

        if(ordenTemporal.getPlatillos().isEmpty()){
            Toast.makeText(this, "No hay Tacos que eliminar en la orden " + ordenId, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent volver = new Intent(this, ModificarOrdenQuitarTActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        volver.putExtra("ordenId", ordenId);
        startActivity(volver);
        this.finish();
    }

    public void modificarOrdenAñadirB(View view){
        ordenId = spnModificarOrdenId.getSelectedItem().toString();

        Intent volver = new Intent(this, ModificarOrdenAnadirBActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        volver.putExtra("ordenId", ordenId);
        startActivity(volver);
        this.finish();
    }

    public void modificarOrdenQuitarB(View view){
        ordenId = spnModificarOrdenId.getSelectedItem().toString();

        int intId = Integer.parseInt(ordenId);
        ClaseOrden ordenTemporal = new ClaseOrden();

        for (int i = 0; i < listaOrdenes.size(); i++) {

            if (listaOrdenes.get(i).getId() == intId){
                ordenTemporal = listaOrdenes.get(i);
                i=listaOrdenes.size();
            }

        }

        if(ordenTemporal.getBebidas().isEmpty()){
            Toast.makeText(this, "No hay Bebidas que eliminar en la orden " + ordenId, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent volver = new Intent(this, ModificarOrdenQuitarBActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        volver.putExtra("ordenId", ordenId);
        startActivity(volver);
        this.finish();
    }

    public void eliminarOrden(View view){
        ordenId = spnModificarOrdenId.getSelectedItem().toString();

        //Mostramos una alerta
        new AlertDialog.Builder(ModificarOrdenActivity.this)
            .setTitle("¿Esta seguro de que quiere eliminar la orden con el id de " + ordenId + "?")
            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    int intId = Integer.parseInt(ordenId);
                    String mesaId = "";

                    //Buscamos el objeto dentro de la lista de ordenes para poder borrarlo
                    for (int ii = 0; ii < listaOrdenes.size(); ii++) {

                        if (listaOrdenes.get(ii).getId() == intId){

                            ClaseMesa mesaTemporal = new ClaseMesa();

                            //Aqui buscamos la mesa que tenga registrada esa cuenta, para borrarla de ahi tambien
                            for (int j = 0; j < arrayMesas.length; j++) {

                                if(arrayMesas[j].getCuentas().isEmpty()){
                                    //Continue para aqui mismo, se brinca el resto de codigo y ejecuta directo el j++
                                    continue;
                                }

                                //Aqui buscamos la orden dentro de la lista de ordenes dentro de cada mesa
                                for (int k = 0; k < arrayMesas[j].getCuentas().size(); k++) {

                                    if(arrayMesas[j].getCuentas().get(k).getId() == intId){
                                        arrayMesas[j].getCuentas().remove(k);
                                        mesaId = Integer.toString(j+1);
                                        j = arrayMesas.length;
                                        //Break destruye el for dentro del que esta (deja de correrlo
                                        break;
                                    }

                                } //for k

                            } //for j

                            Toast.makeText(ModificarOrdenActivity.this, "Se ha removido la orden con el id " + ordenId + " perteneciente a la mesa " + mesaId, Toast.LENGTH_SHORT).show();
                            listaOrdenes.remove(ii);

                            recargar();
                            ii=listaOrdenes.size();

                        }//if for i

                    }//for i

                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                }
        }).create().show();

    }//eliminarOrden

    //Recargamos la pantalla
    public void recargar(){
        Intent elTaco = new Intent(this, ModificarOrdenActivity.class);
        //Nos llevamos todos de paseo
        elTaco.putExtra("listaTacos", listaTacos);
        elTaco.putExtra("listaBebidas", listaBebidas);
        elTaco.putExtra("listaOrdenes", listaOrdenes);
        elTaco.putExtra("arrayMesas", arrayMesas);
        startActivity(elTaco);
        this.finish();
    }

}