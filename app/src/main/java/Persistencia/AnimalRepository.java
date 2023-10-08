package Persistencia;

import com.example.wappnasa.Animal;
import com.j256.ormlite.support.ConnectionSource;

public class AnimalRepository extends Repository<Animal> {
    public AnimalRepository(ConnectionSource c)  {init(Animal.class, c);}
}
