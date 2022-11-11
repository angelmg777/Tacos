package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    Button ingresar, salir;
    ClaseUsuario User;
    ClaseUsuario[] usuarios = new ClaseUsuario[3];
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent;

        //Tacos por defecto
        ArrayList<ClaseTaco> listaTacos = new ArrayList<ClaseTaco>();
        ClaseTaco tacoAsada = new ClaseTaco("Taco de asada", 20);
        ClaseTaco tacoChorizo = new ClaseTaco("Taco de chorizo", 30);
        ClaseTaco tacoPastor = new ClaseTaco("Taco de pastor", 40);
        listaTacos.add(tacoAsada);
        listaTacos.add(tacoChorizo);
        listaTacos.add(tacoPastor);

        //Bebidas por defecto
        ArrayList<ClaseBebida> listaBebidas = new ArrayList<ClaseBebida>();
        ClaseBebida bebidaAguaFrescaChica = new ClaseBebida("Agua Fresca Chica", 20);
        ClaseBebida bebidaRefresco = new ClaseBebida("Refresco", 30);
        ClaseBebida bebidaAguaFrescaGrande = new ClaseBebida("Agua Fresca Grande", 40);
        listaBebidas.add(bebidaAguaFrescaChica);
        listaBebidas.add(bebidaRefresco);
        listaBebidas.add(bebidaAguaFrescaGrande);

        //Inicializacion de lista de ordenes
        ArrayList<ClaseOrden> listaOrdenes = new ArrayList<ClaseOrden>();

        //Inicializacion de array de mesas (porque aqui tiene que haber un numero de mesas por defecto)
        ClaseMesa[] arrayMesas = new ClaseMesa[12];

        if(nuevoUsuario()){
            intent = new Intent(MainActivity.this, MenuActivity.class);
            //Inicialiamos todos las listas y array en el main para asi ya nomas estar paseandolos en las activities
            intent.putExtra("listaTacos", listaTacos);
            intent.putExtra("listaBebidas", listaBebidas);
            intent.putExtra("listaOrdenes", listaOrdenes);
            intent.putExtra("arrayMesas", arrayMesas);
            startActivity(intent);
            finish();
        }else{
            intent = new Intent(MainActivity.this, LoginActivity.class);
            //Inicialiamos todos las listas y array en el main para asi ya nomas estar paseandolos en las activities
            intent.putExtra("listaTacos", listaTacos);
            intent.putExtra("listaBebidas", listaBebidas);
            intent.putExtra("listaOrdenes", listaOrdenes);
            intent.putExtra("arrayMesas", arrayMesas);
            startActivity(intent);
            finish();
        }

        //Esta parte la podemos sacar del if y sigue funcionando igual

    }

    public boolean nuevoUsuario(){
        SharedPreferences preferences = getSharedPreferences("user.dat",MODE_PRIVATE);

        return preferences.getBoolean("registrado", false);
    }


}