package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText user, pass;
    Button ingresar, salir;
    Usuario User;
    Usuario[] usuarios = new Usuario[3];
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Usuario angel = new Usuario("Angel","Angel123");
        Usuario jorge = new Usuario("Jorge","Jorge123");
        Usuario rogelio = new Usuario("Rogelio","Rogelio123");

        usuarios[0] = angel;
        usuarios[1] = jorge;
        usuarios[2] = rogelio;

        user = (EditText) findViewById(R.id.txtUsuario);
        pass = (EditText) findViewById(R.id.txtPassword);
        ingresar = (Button) findViewById(R.id.btnIngresar);
        salir = (Button) findViewById(R.id.btnSalir);



    }



    public void guardarReferencias(Usuario usr){

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
                    User = new Usuario(usuario, password);
                    guardarReferencias(User);

                    Intent intent = new Intent(this, MenuActivity.class);
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