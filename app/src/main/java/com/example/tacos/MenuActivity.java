package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity


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
                break;
            case R.id.itemElTaco:
                Intent elTaco = new Intent(this, EliminarTacoActivity.class);
                //Nos llevamos todos de paseo
                elTaco.putExtra("listaTacos", listaTacos);
                elTaco.putExtra("listaBebidas", listaBebidas);
                elTaco.putExtra("listaOrdenes", listaOrdenes);
                elTaco.putExtra("arrayMesas", arrayMesas);
                startActivity(elTaco);
                break;
            case R.id.itemAgBebida:
                Intent agBebida = new Intent(this, AgregarBebidaActivity.class);
                //Nos llevamos todos de paseo
                agBebida.putExtra("listaTacos", listaTacos);
                agBebida.putExtra("listaBebidas", listaBebidas);
                agBebida.putExtra("listaOrdenes", listaOrdenes);
                agBebida.putExtra("arrayMesas", arrayMesas);
                startActivity(agBebida);
                break;
            case R.id.itemElBebida:
                Intent elBebida = new Intent(this, EliminarBebidaActivity.class);
                //Nos llevamos todos de paseo
                elBebida.putExtra("listaTacos", listaTacos);
                elBebida.putExtra("listaBebidas", listaBebidas);
                elBebida.putExtra("listaOrdenes", listaOrdenes);
                elBebida.putExtra("arrayMesas", arrayMesas);
                startActivity(elBebida);
                break;
            case R.id.itemLogout:
                cerrarSesion();
                break;

        }//switch
        return super.onOptionsItemSelected(item);
    }//Funcion


    /*public void cerrarSesion(View view){
        SharedPreferences preferencias = getSharedPreferences("user.dat",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.clear();
        editor.apply();
        Intent logout = new Intent(this, MainActivity.class);
        logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logout);
        finish();
    }*/ //Comentado para no perderlo, le quite el view view para que pueda ser llamado desde el item del menu Logout

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
}