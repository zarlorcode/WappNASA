package com.example.wappnasa;

import com.j256.ormlite.field.DatabaseField;

public class Zona {
    @DatabaseField(id = true)
    int idZona;
    @DatabaseField
    String descripcion;
    public Zona(){}

    public Zona(int idZona, String descripcion) {
        this.idZona = idZona;
        this.descripcion = descripcion;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
