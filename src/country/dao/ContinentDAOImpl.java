package country.dao;

import country.model.Continent;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ContinentDAOImpl implements ContinentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Continent getByCode(String code) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Continent c where c.code = :code");
        query.setString("code",code);
        return (Continent) query.list().get(0);
    }

    @Override
    public Continent getByID(String id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Continent c where c.id = :id");
        query.setString("id",id);
        return (Continent) query.list().get(0);
    }
}