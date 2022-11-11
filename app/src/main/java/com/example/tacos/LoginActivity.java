package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    //Listas y arrays
    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    //Globales de esta Activity
    EditText user, pass;
    Button ingresar, salir;
    ClaseUsuario User;
    ClaseUsuario[] usuarios = new ClaseUsuario[3];
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Listas y arrays
        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");

        //Configuracion de esta activity
        ClaseUsuario angel = new ClaseUsuario("Angel","Angel123");
        ClaseUsuario jorge = new ClaseUsuario("Jorge","Jorge123");
        ClaseUsuario rogelio = new ClaseUsuario("Rogelio","Rogelio123");

        usuarios[0] = angel;
        usuarios[1] = jorge;
        usuarios[2] = rogelio;

        user = (EditText) findViewById(R.id.txtUsuario);
        pass = (EditText) findViewById(R.id.txtPassword);
        ingresar = (Button) findViewById(R.id.btnIngresar);
        salir = (Button) findViewById(R.id.btnSalir);

    }



    public void guardarReferencias(ClaseUsuario usr){

        SharedPreferences preferences = getSharedPreferences("user.dat",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", usr.getCorreo());
        editor.putString("contraseña", usr.getContraseña());
        editor.putBoolean("registrado", usr.isRegistrado());
        editor.apply();
    }

    public void ingresar(View view) {
        String usuario = user.getText().toString();
        String password = pass.getText().toString();
        //int  cos = Integer.parseInt(contra.getText().toString());

        if (!usuario.matches("") && !password.matches("")) {
            for (int i = 0; i < usuarios.length; i++) {
                if (usuarios[i].getCorreo().equals(usuario) && usuarios[i].getContraseña().equals(password)) {
                    User = new ClaseUsuario(usuario, password);
                    guardarReferencias(User);

                    Intent intent = new Intent(this, MenuActivity.class);

                    //Nos llevamos todos de paseo
                    intent.putExtra("listaTacos", listaTacos);
                    intent.putExtra("listaBebidas", listaBebidas);
                    intent.putExtra("listaOrdenes", listaOrdenes);
                    intent.putExtra("arrayMesas", arrayMesas);

                    startActivity(intent);
                    finish();
                    return;
                }
            }
                Toast toast = Toast.makeText(getApplicationContext(), "La contraseña o el usuario es incorrecto", Toast.LENGTH_SHORT);
                toast.show();
                return;

        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    public void salir(View view){
        finish();
    }

}