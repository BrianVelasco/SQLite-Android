package com.brianvp.proyectosqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.brianvp.proyectosqlite.utilidades.Utilidades;

public class BuscarActivity extends AppCompatActivity {
    ConexionSQLiteHelper conexion;
    EditText id,nombre,tel;
    Button buscar,actualizar,eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        conexion = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);


        id = (EditText) findViewById(R.id.campo_busqueda);
        nombre = (EditText) findViewById(R.id.e_nombre);
        tel = (EditText) findViewById(R.id.e_telefono);
        buscar = (Button) findViewById(R.id.buscar);
        actualizar = (Button) findViewById(R.id.actualizar);
        eliminar = (Button) findViewById(R.id.eliminar);


    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.buscar:
                //consultar();
                consultarSQL();
                break;
            case R.id.actualizar:
                actualizarDatos();
                break;
            case R.id.eliminar:
                eliminarDatos();
                break;
        }
    }

    private void eliminarDatos() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] parametros = {id.getText().toString()};

        db.delete(Utilidades.NOMBRE_TABLA, Utilidades.CAMPO_ID +" =?",parametros);
        Toast.makeText(getApplicationContext(),"Eliminado",Toast.LENGTH_LONG).show();
        db.close();
        limpiar();
    }

    private void actualizarDatos() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] parametros = {id.getText().toString()};

        ContentValues values = new ContentValues();
        //Colocar (campode la tabla, campo nuevo)
        values.put(Utilidades.CAMPO_USUARIO, nombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, tel.getText().toString());

        //mandar a actualizar
        db.update(Utilidades.NOMBRE_TABLA, values, Utilidades.CAMPO_ID +" =?",parametros);

        Toast.makeText(getApplicationContext(),"Actualizado",Toast.LENGTH_LONG).show();
        db.close();
        limpiar();

    }

    private void consultarSQL() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {id.getText().toString()};
        try {
            //SQL
            Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_USUARIO+", "+ Utilidades.CAMPO_TELEFONO+" FROM "+
                    Utilidades.NOMBRE_TABLA+" WHERE "+ Utilidades.CAMPO_ID +" =?", parametros);
            cursor.moveToFirst();
            nombre.setText(cursor.getString(0));
            tel.setText(cursor.getString(1));
            db.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    private void consultar(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Arreglo de los parametros a consultar
        String[] parametros = {id.getText().toString()};
        //Arreglo que queremos ver
        String[] campos = {Utilidades.CAMPO_USUARIO, Utilidades.CAMPO_TELEFONO};
        //todo bien
        try{
            //Cursor con la consulta query(tabla, campos que queremos,campo a comparar, insertado, null,null,nul->son de los order by y eso )
            Cursor cursor = db.query(Utilidades.NOMBRE_TABLA,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            //mover el cursor en el primer registro
            cursor.moveToFirst();
            //Ubicar el resultado en los campos
            nombre.setText(cursor.getString(0));
            tel.setText(cursor.getString(1));
            db.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();

        };
    }
    private void limpiar(){
        nombre.setText("");
        tel.setText("");
    }
}
