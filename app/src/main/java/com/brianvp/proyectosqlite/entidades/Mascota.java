package com.brianvp.proyectosqlite.entidades;

import java.io.Serializable;

/**
 * Created by IBVP on 29/10/2018.
 */

public class Mascota implements Serializable {
    int id , idDueño;
    String nombreMascota, raza;

    public Mascota(int id, int idDueño, String nombreMascota, String raza) {
        this.id = id;
        this.idDueño = idDueño;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
    }
    public Mascota(){

    }

    public int getId() {
        return id;
    }

    public int getIdDueño() {
        return idDueño;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdDueño(int idDueño) {
        this.idDueño = idDueño;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
