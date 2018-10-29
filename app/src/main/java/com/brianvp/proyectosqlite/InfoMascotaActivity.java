package com.brianvp.proyectosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.brianvp.proyectosqlite.entidades.Mascota;
import com.brianvp.proyectosqlite.utilidades.Utilidades;

public class InfoMascotaActivity extends AppCompatActivity {
    TextView nMascota, rMascota, idDuenio, nDuenio, tDuenio;
    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mascota);
        conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);
        nMascota = (TextView) findViewById(R.id.campoNombreMascota);
        rMascota = (TextView) findViewById(R.id.campoRaza);

        idDuenio = (TextView) findViewById(R.id.idDuenio);
        nDuenio = (TextView) findViewById(R.id.nombreDuenio);
        tDuenio = (TextView) findViewById(R.id.telDuenio);

        Bundle recibido = getIntent().getExtras();
        Mascota mascota = null;

        if (recibido != null) {
            mascota = (Mascota) recibido.getSerializable("mascota");
            nMascota.setText(mascota.getNombreMascota().toString());
            rMascota.setText(mascota.getRaza().toString());
            consultarDueño(mascota.getIdDueño());
        }


    }

    private void consultarDueño(Integer idDueño) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {idDueño.toString()};
        try {
            //SQL
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_USUARIO + ", " + Utilidades.CAMPO_TELEFONO + " FROM " +
                    Utilidades.NOMBRE_TABLA + " WHERE " + Utilidades.CAMPO_ID + " =?", parametros);
            cursor.moveToFirst();
            idDuenio.setText(idDueño.toString());
            nDuenio.setText(cursor.getString(0));
            tDuenio.setText(cursor.getString(1));
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();

        }
    }
}
