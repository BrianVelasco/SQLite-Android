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
import android.widget.Toast;

import com.brianvp.proyectosqlite.entidades.Usuario;
import com.brianvp.proyectosqlite.utilidades.Utilidades;
import com.brianvp.proyectosqlite.utilidades.VistaListaActivity;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    ListView lista;
    ArrayList<String> listaInformacion ;
    ArrayList<Usuario> listaPersonas;
    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lista = (ListView) findViewById(R.id.listaUsuarios);

        conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        consultarLista();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String informacion = "Documento: "+ listaPersonas.get(i).getId()+"\n";
                informacion += "Nombre: "+ listaPersonas.get(i).getNombre()+"\n";
                informacion += "Telefono: "+  listaPersonas.get(i).getTelefono()+"\n";

                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                Usuario user = listaPersonas.get(i);

                Intent intent = new Intent(ListViewActivity.this, VistaListaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", user);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });




    }

    private void consultarLista() {
        SQLiteDatabase db = conexion.getReadableDatabase();

        Usuario usuario = null;
        listaPersonas = new ArrayList<Usuario>();
        //SQL
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.NOMBRE_TABLA , null);

        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaPersonas.add(usuario);
        }
        obtenerLista();


    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i=0; i < listaPersonas.size() ; i++){
            listaInformacion.add(listaPersonas.get(i).getId()+ " --- "+ listaPersonas.get(i).getNombre());
        }

    }
}
