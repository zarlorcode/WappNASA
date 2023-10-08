package Persistencia;

import com.example.wappnasa.Animal;
import com.example.wappnasa.Punto;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class PuntoRepository extends Repository<Punto> {
    public PuntoRepository(ConnectionSource c)  {init(Punto.class, c);}

    public List<Punto> obtenerPuntosZona(int zona){
        try {
            return this.getDao().queryForEq("zona", zona);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
