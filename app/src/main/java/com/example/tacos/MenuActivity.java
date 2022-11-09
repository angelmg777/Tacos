package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
                /*Intent pedido = new Intent(this, Pedido.class);
                pedido.putExtra("disfraz",disfraz);
                startActivity(pedido);*/
                break;
            case R.id.itemElTaco:
                /*Intent productos = new Intent(this, Productos.class);
                startActivity(productos);*/
                break;
            case R.id.itemAgBebida:
                /*Intent compras = new Intent(this, Compras.class);
                compras.putExtra("disfraz",disfraz);
                startActivity(compras);*/
                break;
            case R.id.itemElBebida:
                /*Intent nosotros = new Intent(this, Nosotros.class);
                startActivity(nosotros);*/
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