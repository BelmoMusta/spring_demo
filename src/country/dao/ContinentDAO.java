package country.dao;

import country.model.Continent;

public interface ContinentDAO {
    Continent getContientByName(String name);
<<<<<<< HEAD
    boolean exists(String code);
    Continent getContientByCode(String code);
    boolean existsByName(String name);
}
=======
    boolean existsByName(String name);
    }
>>>>>>> premier_aspect_fonctionnel
