package com.brianvp.proyectosqlite.entidades;

import java.io.Serializable;

/**
 * Created by IBVP on 28/10/2018.
 */
//Serializable para poder enviar
public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String telefono;

    public Usuario (int id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public Usuario(){

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
