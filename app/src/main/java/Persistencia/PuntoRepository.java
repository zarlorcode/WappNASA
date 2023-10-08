package Persistencia;

import com.example.wappnasa.Animal;
import com.example.wappnasa.Punto;
import com.j256.ormlite.support.ConnectionSource;

public class PuntoRepository extends Repository<Punto> {
    public PuntoRepository(ConnectionSource c)  {init(Punto.class, c);}
}
