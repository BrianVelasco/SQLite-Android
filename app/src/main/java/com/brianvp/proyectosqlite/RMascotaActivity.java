package com.brianvp.proyectosqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.brianvp.proyectosqlite.entidades.Usuario;
import com.brianvp.proyectosqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class RMascotaActivity extends AppCompatActivity {

    EditText nMascota,rMascota;
    Spinner listaDueños;
    ConexionSQLiteHelper conexion;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmascota);
        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        nMascota =(EditText) findViewById(R.id.nombreMascota);
        rMascota = (EditText) findViewById(R.id.razaMascota);
        listaDueños = (Spinner) findViewById(R.id.comboDueños);

        consultarPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaPersonas);
        listaDueños.setAdapter(adaptador);


    }

    private void consultarPersonas() {
        SQLiteDatabase bd = conexion.getReadableDatabase();

        Usuario persona = null;
        personas = new ArrayList<Usuario>();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + Utilidades.NOMBRE_TABLA, null);
        while (cursor.moveToNext()) {
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
    public void onClick (View v){
        registrarMascota();
    }

    private void registrarMascota() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        //CAPTURAMOS POS DEL ITEM SELECCIONADO
        int idCombo = (int) listaDueños.getSelectedItemId();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA, nMascota.getText().toString());
        values.put(Utilidades.CAMPO_RAZA,rMascota.getText().toString());
        //SI NO ES SELECCIONE
        if(idCombo !=0){
            int idDueño = personas.get(idCombo-1).getId();
            values.put(Utilidades.CAMPO_ID_DUENIO,idDueño);

            //(TABLA, DEVOLUCION,VALORES)
            Long id_resultante = db.insert(Utilidades.TABLA_MASCOTA,Utilidades.CAMPO_ID_MASCOTA,values);
            Toast.makeText(getApplicationContext(),"ID_Registrado:"+ id_resultante,Toast.LENGTH_LONG).show();

            db.close();

        }
        else {
            Toast.makeText(getApplicationContext(),"Debe Seleccionar un dueño",Toast.LENGTH_LONG).show();
        }

    }

}
