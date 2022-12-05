package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MesaTotalActivity extends AppCompatActivity {

    ArrayList<ClaseBebida> listaBebidas;
    ArrayList<ClaseOrden> listaOrdenes;
    ArrayList<ClaseTaco> listaTacos;
    ClaseMesa[] arrayMesas;

    String ordenId;
    TextView totalMesa;
    Button volver, pagada;

    int iteM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_total);

        listaTacos = (ArrayList<ClaseTaco>) getIntent().getSerializableExtra("listaTacos");
        listaBebidas = (ArrayList<ClaseBebida>) getIntent().getSerializableExtra("listaBebidas");
        listaOrdenes = (ArrayList<ClaseOrden>) getIntent().getSerializableExtra("listaOrdenes");
        arrayMesas = (ClaseMesa[]) getIntent().getSerializableExtra("arrayMesas");
        int item = (int) getIntent().getSerializableExtra("item");

        totalMesa = (TextView) findViewById(R.id.txtTotal);
        volver = (Button) findViewById(R.id.btnVolverTotal);
        pagada = (Button) findViewById(R.id.btnPagada);


        if(arrayMesas[item].cuentas.isEmpty()){
            NoHay(item);
        }else {

            String res = "";
            int total = 0;

            res+="\n-------Tacos Bala-------\n";
            res+="Cuenta total de la Mesa   "+(item+1)+"\n\n";

            for (int i = 0; i < arrayMesas[item].cuentas.size(); i++) {
                for (int j = 0; j < arrayMesas[item].cuentas.get(i).getPlatillos().size(); j++) {
                    total = total + arrayMesas[item].cuentas.get(i).getPlatillos().get(j).getPrecio();
                    res += (arrayMesas[item].cuentas.get(i).getPlatillos().get(j).getNombre() + "          $" + arrayMesas[item].cuentas.get(i).getPlatillos().get(j).getPrecio() + "\n");
                }
                for (int k = 0; k < arrayMesas[item].cuentas.get(i).getBebidas().size(); k++) {
                    total = total + arrayMesas[item].cuentas.get(i).getBebidas().get(k).getPrecio();
                    res += (arrayMesas[item].cuentas.get(i).getBebidas().get(k).getNombre() + "           $" + arrayMesas[item].cuentas.get(i).getBebidas().get(k).getPrecio() + "\n");
                }
            }

            double propina = total * 0.15;
            res += ("\nTotal a Pagar:       $" + total);
            res += ("\nPropina sugerida 15%=   $" + propina);



            totalMesa.setText(res);
        }
    }




    public void volver(View view){
        Intent volver = new Intent(this, MesasActivity.class);
        //Nos llevamos todos de paseo
        volver.putExtra("listaTacos", listaTacos);
        volver.putExtra("listaBebidas", listaBebidas);
        volver.putExtra("listaOrdenes", listaOrdenes);
        volver.putExtra("arrayMesas", arrayMesas);
        startActivity(volver);
        this.finish();
    }

    public void NoHay(int item){
        Intent intent = new Intent(this, MesasActivity.class);
        //Nos llevamos todos de paseo
        intent.putExtra("listaTacos", listaTacos);
        intent.putExtra("listaBebidas", listaBebidas);
        intent.putExtra("listaOrdenes", listaOrdenes);
        intent.putExtra("arrayMesas", arrayMesas);
        intent.putExtra("item", item);
        startActivity(intent);
        this.finish();

        Toast toast = Toast.makeText(getApplicationContext(), "No hay ordenes en esta mesa", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void pagar(View view) {
        int item = (int) getIntent().getSerializableExtra("item");

        if (arrayMesas[item].cuentas.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Esta cuenta ya fue pagada", Toast.LENGTH_SHORT);
            toast.show();
        } else {













































































            new AlertDialog.Builder(MesaTotalActivity.this)
                    .setTitle("Se pago la cuenta de essta mesa?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            while (!arrayMesas[item].cuentas.isEmpty()) {
                                int x = 0;
                                arrayMesas[item].cuentas.remove(x);
                                x++;
                            }

                            Toast toast = Toast.makeText(getApplicationContext(), "Cuenta Pagada", Toast.LENGTH_SHORT);
                            toast.show();

                            volver(view);

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        }


    }




}