package com.brianvp.proyectosqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.brianvp.proyectosqlite.utilidades.Utilidades;

public class RegistrarActivity extends AppCompatActivity {
    EditText nombre,id,telefono;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nombre = (EditText) findViewById(R.id.nombre);
        id = (EditText) findViewById(R.id.id);
        telefono = (EditText)findViewById(R.id.tel);
        registrar = (Button) findViewById(R.id.registrar);
    }

    public void onClick(View v){
        //registrar_usuario();//Metodo_1
        registrarUsuariosSql();
    }

    private void registrar_usuario(){
        //conectar
        ConexionSQLiteHelper conexion= new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        //abrir bd para editarla
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues values = new ContentValues(); //Creamos el contenedor para los valores
        values.put(Utilidades.CAMPO_ID,id.getText().toString());
        values.put(Utilidades.CAMPO_USUARIO,nombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,telefono.getText().toString());

        //insertar en la base de datos (nombreTalba, devolucion, valores_A_insertar)

        Long id_resultante = db.insert(Utilidades.NOMBRE_TABLA,Utilidades.CAMPO_ID,values);
        Toast.makeText(getApplicationContext(),"ID_Registrado:"+ id_resultante,Toast.LENGTH_LONG).show();

        db.close();
    }
    private void registrarUsuariosSql(){
        //conectar
        ConexionSQLiteHelper conexion= new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        //abrir bd para editarla
        SQLiteDatabase db = conexion.getWritableDatabase();
        //SQL
        String inserts = "INSERT INTO "+Utilidades.NOMBRE_TABLA+" ( "+
                Utilidades.CAMPO_ID+", "+Utilidades.CAMPO_USUARIO+", "+Utilidades.CAMPO_TELEFONO+" ) VALUES ("+
                id.getText().toString()+", '"+nombre.getText().toString()+"', '"+telefono.getText().toString()+"' );";
        db.execSQL(inserts);
        db.close();

    }
}
