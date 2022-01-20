package country.dao.impl;

import country.dao.ICountryDAO;
import country.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    @Override
    public Country getByCode(String code) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Country> cr = builder.createQuery(Country.class);

        Root<Country> root = cr.from(Country.class);
        cr.select(root).where(builder.equal(root.get("code"), code));

        Country country = session.createQuery(cr).uniqueResult();

        session.close();
        return country;
    }
}
