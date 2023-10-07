package com.example.wappnasa;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable
public class Animal {
    @DatabaseField(id = true)
    int idAnimal;
    @DatabaseField
    String nombre;
    @DatabaseField
    double latitud;
    @DatabaseField
    double longitud;
    @DatabaseField
    String descripcion;
    @DatabaseField
    String imagen;

    public Animal(){}
    public Animal(int idAnimal, String nombre, double latitud, double longitud, String descripcion, String imagen) {
        this.idAnimal = idAnimal;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
