package com.brianvp.proyectosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.brianvp.proyectosqlite.utilidades.Utilidades;

/**
 * Created by IBVP on 28/10/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper{



    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.crear_tabla);
        db.execSQL(Utilidades.crear_tabla_mascotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXIST " + Utilidades.NOMBRE_TABLA);
        db.execSQL("DROP TABLE IF EXIST "+ Utilidades.TABLA_MASCOTA);
        onCreate(db);

    }
}
