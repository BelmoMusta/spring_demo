package country.dao.impl;

import country.dao.ICountryDAO;
import country.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAO implements ICountryDAO {
    static SessionFactory sessionFactory;

    static {
        if(sessionFactory == null) {
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            sessionFactory  = config.buildSessionFactory();
        }
    }

    @Override
    public Country add(Country country) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(country);
        session.getTransaction().commit();
        session.close();
        return country;
    }
}
