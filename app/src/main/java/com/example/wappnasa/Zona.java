package com.example.wappnasa;

import com.j256.ormlite.field.DatabaseField;

public class Zona {
    @DatabaseField(id = true)
    int idZona;

    @DatabaseField
    String nombre;
    @DatabaseField
    String descripcion;
    @DatabaseField
    String color;
    @DatabaseField
    String estado;
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
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
