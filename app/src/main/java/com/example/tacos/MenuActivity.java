package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    ImageButton imbMostrarOrdenes, imbAdministrarOrdenes, imbMesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta activity
        imbMesas = (ImageButton) findViewById(R.id.imbMesas);
        imbAdministrarOrdenes = (ImageButton) findViewById(R.id.imbAdministrarOrdenes);
        imbMostrarOrdenes = (ImageButton) findViewById(R.id.imbMostrarOrdenes);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);

        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.itemAgTaco:
                Intent agTaco = new Intent(this, AgregarTacoActivity.class);
                //Nos llevamos todos de paseo
                agTaco.putExtra("listaTacos", listaTacos);
                agTaco.putExtra("listaBebidas", listaBebidas);
                agTaco.putExtra("listaOrdenes", listaOrdenes);
                agTaco.putExtra("arrayMesas", arrayMesas);
                startActivity(agTaco);
                this.finish();
                break;
            case R.id.itemElTaco:

                if(listaTacos.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "¡No hay tacos para eliminar!", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent elTaco = new Intent(this, EliminarTacoActivity.class);
                    //Nos llevamos todos de paseo
                    elTaco.putExtra("listaTacos", listaTacos);
                    elTaco.putExtra("listaBebidas", listaBebidas);
                    elTaco.putExtra("listaOrdenes", listaOrdenes);
                    elTaco.putExtra("arrayMesas", arrayMesas);
                    startActivity(elTaco);
                    this.finish();
                }
                break;

            case R.id.itemAgBebida:
                Intent agBebida = new Intent(this, AgregarBebidaActivity.class);
                //Nos llevamos todos de paseo
                agBebida.putExtra("listaTacos", listaTacos);
                agBebida.putExtra("listaBebidas", listaBebidas);
                agBebida.putExtra("listaOrdenes", listaOrdenes);
                agBebida.putExtra("arrayMesas", arrayMesas);
                startActivity(agBebida);
                this.finish();
                break;
            case R.id.itemElBebida:

                if(listaBebidas.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "¡No hay bebidas para eliminar!", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent elBebida = new Intent(this, EliminarBebidaActivity.class);
                    //Nos llevamos todos de paseo
                    elBebida.putExtra("listaTacos", listaTacos);
                    elBebida.putExtra("listaBebidas", listaBebidas);
                    elBebida.putExtra("listaOrdenes", listaOrdenes);
                    elBebida.putExtra("arrayMesas", arrayMesas);
                    startActivity(elBebida);
                    this.finish();
                }
                break;

            case R.id.itemLogout:
                cerrarSesion();
                break;

        }//switch
        return super.onOptionsItemSelected(item);
    }//Funcion

    public void cerrarSesion() {
        SharedPreferences preferencias = getSharedPreferences("user.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.clear();
        editor.apply();
        Intent logout = new Intent(this, MainActivity.class);
        logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logout);
        finish();
    }

    public void mesas(View view){
        Intent intent = new Intent(this, MesasActivity.class);
        //Nos llevamos todos de paseo
        intent.putExtra("listaTacos", listaTacos);
        intent.putExtra("listaBebidas", listaBebidas);
        intent.putExtra("listaOrdenes", listaOrdenes);
        intent.putExtra("arrayMesas", arrayMesas);
        startActivity(intent);
        this.finish();
    }

    public void mostrarOrdenes(View view){

        if(listaOrdenes.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "¡No hay Ordenes!", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Intent intent = new Intent(this, MostrarOrdenesActivity.class);
            //Nos llevamos todos de paseo
            intent.putExtra("listaTacos", listaTacos);
            intent.putExtra("listaBebidas", listaBebidas);
            intent.putExtra("listaOrdenes", listaOrdenes);
            intent.putExtra("arrayMesas", arrayMesas);
            startActivity(intent);
            this.finish();
        }
    }

    public void administrarOrdenes(View view){
        Intent intent = new Intent(this, AdministrarOrdenActivity.class);
        //Nos llevamos todos de paseo
        intent.putExtra("listaTacos", listaTacos);
        intent.putExtra("listaBebidas", listaBebidas);
        intent.putExtra("listaOrdenes", listaOrdenes);
        intent.putExtra("arrayMesas", arrayMesas);
        startActivity(intent);
        this.finish();
    }

}