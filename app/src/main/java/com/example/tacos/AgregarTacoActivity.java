package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AgregarTacoActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    EditText edtNombreTaco, edtPrecioTaco;
    Button btnAgregar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_taco);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta Activity
        edtNombreTaco = (EditText) findViewById(R.id.edtNombreTaco);
        edtPrecioTaco = (EditText) findViewById(R.id.edtPrecioTaco);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnVolver = (Button) findViewById(R.id.btnVolverAgTaco);

    }

    public void agregar(View view){
        String nombre = edtNombreTaco.getText().toString();

        //Simple toast por si uno esta vacio
        if(nombre.equals("") || edtPrecioTaco.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        int precio = Integer.parseInt(edtPrecioTaco.getText().toString());

        //Aqui nomas checamos que no se repita el nombre
        for (int i = 0; i < listaTacos.size(); i++){
            if(listaTacos.get(i).getNombre().equals(nombre)) {
                Toast toast = Toast.makeText(getApplicationContext(), nombre + " ya es parte del menu", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        }

        //Si no, lo agregamos a la lista
        ClaseTaco nuevoTaco = new ClaseTaco(nombre, precio);
        listaTacos.add(nuevoTaco);

        //Prueba
        String ser = SerializableObject.objectToString(listaTacos);
        if (ser != null && !ser.equalsIgnoreCase("")) {
            SerializableObject.WriteSettings(AgregarTacoActivity.this, ser, "myobject.dat");
        } else {
            SerializableObject.WriteSettings(AgregarTacoActivity.this, "", "myobject.dat");
        }

        //Limpiamos
        edtNombreTaco.setText("");
        edtPrecioTaco.setText("");

        Toast toast = Toast.makeText(getApplicationContext(), nuevoTaco.getNombre() + " ha sido agregado al menu", Toast.LENGTH_SHORT);
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