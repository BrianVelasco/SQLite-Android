package com.brianvp.proyectosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.brianvp.proyectosqlite.entidades.Usuario;
import com.brianvp.proyectosqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {
    Spinner combo;
    TextView id,nombre,telefono;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personas;
    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        

        combo =(Spinner) findViewById(R.id.comboUsuarios);
        id =(TextView) findViewById(R.id.campoDocumento);
        nombre =(TextView) findViewById(R.id.campoNombre);
        telefono =(TextView) findViewById(R.id.campoTelefono);

        consultarPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaPersonas);
        combo.setAdapter(adaptador);

        //Seleccionar un item

        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                if(pos!=0){
                    id.setText(personas.get(pos-1).getId()+"");
                    nombre.setText(personas.get(pos-1).getNombre());
                    telefono.setText(personas.get(pos-1).getTelefono());
                }else {
                    id.setText("");
                    nombre.setText("");
                    telefono.setText("");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consultarPersonas() {
        SQLiteDatabase bd = conexion.getReadableDatabase();

        Usuario persona =null;
        personas = new ArrayList<Usuario>();

        Cursor cursor = bd.rawQuery("SELECT * FROM "+ Utilidades.NOMBRE_TABLA,null);
        while(cursor.moveToNext()){
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            personas.add(persona);

        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for(int i=0;i<personas.size();i++){
            //Obtener cada usuario en la pos i y a ese obtener id y nombre
            listaPersonas.add(personas.get(i).getId() +" -- "+ personas.get(i).getNombre());

        }
    }
}
