package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    Button ingresar, salir;
    Usuario User;
    Usuario[] usuarios = new Usuario[3];
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent;


        if(nuevoUsuario()){

            intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }else{

            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();


        }
    }

    public boolean nuevoUsuario(){
        SharedPreferences preferences = getSharedPreferences("user.dat",MODE_PRIVATE);

        return preferences.getBoolean("registrado", false);
    }


}