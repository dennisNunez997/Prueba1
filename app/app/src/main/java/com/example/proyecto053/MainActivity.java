package com.example.proyecto053;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;º
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Definimos las variables que almacenaran los telefonos y el adptador entre el ListView y el ArrayList
    private ArrayList<String> telefonos;
    private ArrayAdapter<String> adaptador1;
    //Definimos las variables globales
    private ListView lv1;
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creamos el ArrayList
        telefonos=new ArrayList<String>();
        //Cramos el adaptador y lo presentamos
        adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,telefonos);
        //Obtenemos la referencia del ListView y le asociamos el ArrayAdapter para que pueda recibir los items a mostrar
        lv1=(ListView)findViewById(R.id.listView);
        lv1.setAdapter(adaptador1);

        et1=(EditText)findViewById(R.id.editText);

        //En una pulsacion larga del item de la lista se crea una notificacion para asegurarse o no de eliminar el elemento
        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                //Mensaje
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este teléfono ?");
                //Cancelar
                dialogo1.setCancelable(false);
                //Aceptar
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    //Eliminacion del item seleccionado
                    public void onClick(DialogInterface dialogo1, int id) {
                        telefonos.remove(posicion);
                        adaptador1.notifyDataSetChanged();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    //Cancelacion de la acccion
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });
    }

    //Agrega el item a la lista y notifica al listView de actualizarse
    public void agregar(View v) {
        //adicion
        telefonos.add(et1.getText().toString());
        //notificacion
        adaptador1.notifyDataSetChanged();
        //limpieza de edittext
        et1.setText("");
    }
}
