package com.brianvp.proyectosqlite.utilidades;

/**
 * Created by IBVP on 28/10/2018.
 */

public class Utilidades {
    //CONSTANTES TABLA USUARIO
    public static final String NOMBRE_TABLA = "usuarios";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_USUARIO = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";

    public static final String crear_tabla = "CREATE TABLE " + NOMBRE_TABLA + "("+CAMPO_ID+" INTEGER, "+CAMPO_USUARIO+" TEXT, "+CAMPO_TELEFONO+" TEXT );";

    public static final String TABLA_MASCOTA = "mascotas";
    public static final String CAMPO_ID_MASCOTA = "id_mascota";
    public static final String CAMPO_NOMBRE_MASCOTA = "nombre_mascota";
    public static final String CAMPO_RAZA = "raza_mascota";
    public static final String CAMPO_ID_DUENIO = "id_duenio";

    public static final String crear_tabla_mascotas = "CREATE TABLE " + TABLA_MASCOTA + "("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE_MASCOTA+" TEXT, "+CAMPO_RAZA+" TEXT ,"+CAMPO_ID_DUENIO+" INTEGER );";

}
