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
}
