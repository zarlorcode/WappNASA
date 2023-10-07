package com.example.wappnasa;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable
public class Punto {
    @DatabaseField(id = true)
    int idPunto;
    @DatabaseField
    double latitud;
    @DatabaseField
    double longitud;
    @DatabaseField
    int zona;
    public Punto(){}
    public Punto(int idPunto, double latitud, double longitud, int zona) {
        this.idPunto = idPunto;
        this.latitud = latitud;
        this.longitud = longitud;
        this.zona = zona;
    }

    public int getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(int idPunto) {
        this.idPunto = idPunto;
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

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }
}
