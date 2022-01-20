package country.dao.impl;

import country.dao.IContinentDAO;
import country.model.Continent;
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
public class ContinentDAO implements IContinentDAO {
    static SessionFactory sessionFactory;

    static {
        if(sessionFactory == null) {
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            sessionFactory  = config.buildSessionFactory();
        }
    }

    @Override
    public Continent add(Continent continent) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(continent);
        session.getTransaction().commit();
        session.close();
        return continent;
    }

    @Override
    public Continent getByName(String name) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Continent> cr = builder.createQuery(Continent.class);

        Root<Continent> root = cr.from(Continent.class);
        cr.select(root).where(builder.equal(root.get("name"), name));

        Continent continent = session.createQuery(cr).uniqueResult();

        session.close();
        return continent;
    }

    @Override
    public List<Continent> getAll() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Continent> cq = cb.createQuery(Continent.class);
        Root<Continent> rootEntry = cq.from(Continent.class);
        CriteriaQuery<Continent> all = cq.select(rootEntry);

        TypedQuery<Continent> allQuery = session.createQuery(all);

        List<Continent> continents = allQuery.getResultList();

        return continents;
    }


}
