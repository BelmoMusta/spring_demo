package country.configuration;


import country.dao.IContinentDAO;
import country.dao.impl.ContinentDAO;
import country.model.Continent;
import org.springframework.context.ApplicationContext;

public class InitDatabase {
    public static void initContinents(ApplicationContext applicationContext) {
        IContinentDAO continentDAO = applicationContext.getBean(ContinentDAO.class);
        continentDAO.add(new Continent("AF", "Afrique"));
        continentDAO.add(new Continent("AN", "Antarctique"));
        continentDAO.add(new Continent("AS", "Asie"));
        continentDAO.add(new Continent("EU", "Europe"));
        continentDAO.add(new Continent("NA", "Amérique du Nord"));
        continentDAO.add(new Continent("OC", "Océanie"));
        continentDAO.add(new Continent("SA", "Amérique du Sud"));
    }
}
