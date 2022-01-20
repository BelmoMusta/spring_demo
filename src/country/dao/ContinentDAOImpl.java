package country.dao;


import country.dao.ContinentDAO;
import country.model.Continent;
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
    SessionFactory sessionFactory;

    @Override
    public Continent getContient(String codeContient) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Continent c where c.code = :code");
        query.setString("code", codeContient);
        return (Continent) query.list().get(0) ;
    }
}
