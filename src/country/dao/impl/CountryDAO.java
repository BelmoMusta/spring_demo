package country.dao.impl;

import country.dao.ICountryDAO;
import country.model.Continent;
import country.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
    public Country update(Country country) {
        if(country.getId() == null) {
            return null;
        }
        System.out.println(country.getId());

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(country);
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

    @Override
    public Country deleteByCode(String code) {
        Country countryToDelete = getByCode(code);
        if(countryToDelete == null) return null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(countryToDelete);
        session.getTransaction().commit();
        session.close();
        return countryToDelete;
    }

    @Override
    public List<Country> getByContinent(Continent continent) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Country> cr = builder.createQuery(Country.class);
        Root<Country> root = cr.from(Country.class);
        CriteriaQuery<Country> all = cr.select(root).where(builder.equal(root.get("continent"), continent));
        TypedQuery<Country> allQuery = session.createQuery(all);
        List<Country> countries = allQuery.getResultList();
        return countries;
    }

}
