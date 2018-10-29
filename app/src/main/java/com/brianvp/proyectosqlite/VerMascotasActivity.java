package com.brianvp.proyectosqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.brianvp.proyectosqlite.entidades.Mascota;
import com.brianvp.proyectosqlite.entidades.Usuario;
import com.brianvp.proyectosqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class VerMascotasActivity extends AppCompatActivity {
    ListView lista;
    ArrayList<String> listaInformacion ;
    ArrayList<Mascota> listaMascotas;
    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_mascotas);
        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        lista = (ListView) findViewById(R.id.listaMascotas);


        consultarLista();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mascota mascota = listaMascotas.get(i);
                Intent intent = new Intent(VerMascotasActivity.this, InfoMascotaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mascota",mascota);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    private void consultarLista() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        listaMascotas= new ArrayList<Mascota>();
        Mascota mascota = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA,null);
        while (cursor.moveToNext()){
            mascota = new Mascota();
            mascota.setId(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDueño(cursor.getInt(3));
            listaMascotas.add(mascota);
        }
        obtenerMascotas();
    }


    private void obtenerMascotas() {
        listaInformacion = new ArrayList<String >();
        for (int i=0 ; i < listaMascotas.size(); i++){
            listaInformacion.add(listaMascotas.get(i).getId()+" ------ " + listaMascotas.get(i).getNombreMascota());
        }
    }


}
