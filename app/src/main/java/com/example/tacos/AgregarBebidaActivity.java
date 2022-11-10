package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AgregarBebidaActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    EditText edtNombreBebida, edtPrecioBebida;
    Button btnAgregar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_bebida);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta Activity
        edtNombreBebida = (EditText) findViewById(R.id.edtNombreBebida);
        edtPrecioBebida = (EditText) findViewById(R.id.edtPrecioBebida);
        btnAgregar = (Button) findViewById(R.id.btnAgregarBebida);
        btnVolver = (Button) findViewById(R.id.btnVolverAgBebida);

    }

    public void agregar(View view){
        String nombre = edtNombreBebida.getText().toString();

        //Simple toast por si uno esta vacio
        if(nombre.equals("") || edtPrecioBebida.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        int precio = Integer.parseInt(edtPrecioBebida.getText().toString());

        //Aqui nomas checamos que no se repita el nombre
        for (int i = 0; i < listaBebidas.size(); i++){
            if(listaBebidas.get(i).getNombre().equals(nombre)) {
                Toast toast = Toast.makeText(getApplicationContext(), nombre + " ya es parte del menu", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        }

        //Si no, lo agregamos a la lista
        ClaseBebida nuevaBebida = new ClaseBebida(nombre, precio);
        listaBebidas.add(nuevaBebida);

        //Limpiamos
        edtNombreBebida.setText("");
        edtPrecioBebida.setText("");

        Toast toast = Toast.makeText(getApplicationContext(), nombre + " ha sido agregado al menu", Toast.LENGTH_SHORT);
        toast.show();
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

}