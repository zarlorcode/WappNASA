package com.example.wappnasa;

import com.j256.ormlite.field.DatabaseField;

public class Zona {
    @DatabaseField(id = true)
    int idZona;
    @DatabaseField
    String descripcion;
}
