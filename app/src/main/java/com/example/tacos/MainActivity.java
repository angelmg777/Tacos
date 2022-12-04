package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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


        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent;

                //Tacos por defecto
                ArrayList<ClaseTaco> listaTacos = new ArrayList<ClaseTaco>();
        /*ClaseTaco tacoAsada = new ClaseTaco("Taco de asada", 20);
        ClaseTaco tacoChorizo = new ClaseTaco("Taco de chorizo", 30);
        ClaseTaco tacoPastor = new ClaseTaco("Taco de pastor", 40);
        listaTacos.add(tacoAsada);
        listaTacos.add(tacoChorizo);
        listaTacos.add(tacoPastor);*/
                String ser = SerializableObject.ReadSettings(MainActivity.this, "myobject.dat");
                if (ser != null && !ser.equalsIgnoreCase("")) {
                    Object obj = SerializableObject.stringToObject(ser);
                    // Then cast it to your object and
                    if (obj instanceof ArrayList) {
                        // Do something
                        listaTacos = (ArrayList<ClaseTaco>)obj;
                    }
                }

                //Bebidas por defecto
                ArrayList<ClaseBebida> listaBebidas = new ArrayList<ClaseBebida>();
        /*ClaseBebida bebidaAguaFrescaChica = new ClaseBebida("Agua Fresca Chica", 20);
        ClaseBebida bebidaRefresco = new ClaseBebida("Refresco", 30);
        ClaseBebida bebidaAguaFrescaGrande = new ClaseBebida("Agua Fresca Grande", 40);
        listaBebidas.add(bebidaAguaFrescaChica);
        listaBebidas.add(bebidaRefresco);
        listaBebidas.add(bebidaAguaFrescaGrande);*/
                String serB = SerializableObject.ReadSettings(MainActivity.this, "myobject2.dat");
                if (serB != null && !serB.equalsIgnoreCase("")) {
                    Object obj = SerializableObject.stringToObject(serB);
                    // Then cast it to your object and
                    if (obj instanceof ArrayList) {
                        // Do something
                        listaBebidas = (ArrayList<ClaseBebida>)obj;
                    }
                }

                //Inicializacion de lista de ordenes
                ArrayList<ClaseOrden> listaOrdenes = new ArrayList<ClaseOrden>();

                //Inicializacion de array de mesas (porque aqui tiene que haber un numero de mesas por defecto)
                ClaseMesa[] arrayMesas = new ClaseMesa[12];

                //Las inicializamos todas con el constructor por defecto, para que así se inicialize su lista de ordenes
                arrayMesas[0] = new ClaseMesa();
                arrayMesas[1] = new ClaseMesa();
                arrayMesas[2] = new ClaseMesa();
                arrayMesas[3] = new ClaseMesa();
                arrayMesas[4] = new ClaseMesa();
                arrayMesas[5] = new ClaseMesa();
                arrayMesas[6] = new ClaseMesa();
                arrayMesas[7] = new ClaseMesa();
                arrayMesas[8] = new ClaseMesa();
                arrayMesas[9] = new ClaseMesa();
                arrayMesas[10] = new ClaseMesa();
                arrayMesas[11] = new ClaseMesa();

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

            }
        }; //tarea

        //Instancia tiempo
        Timer tiempo = new Timer();
        //Definir el tiempo de ejecución para lanzar hilo
        tiempo.schedule(tarea,2000);






    }

    public boolean nuevoUsuario(){
        SharedPreferences preferences = getSharedPreferences("user.dat",MODE_PRIVATE);

        return preferences.getBoolean("registrado", false);
    }


}