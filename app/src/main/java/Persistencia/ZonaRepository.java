package Persistencia;

import com.example.wappnasa.Animal;
import com.example.wappnasa.Zona;
import com.j256.ormlite.support.ConnectionSource;

public class ZonaRepository extends Repository<Zona> {
    public ZonaRepository(ConnectionSource c)  {init(Zona.class, c);}
}
