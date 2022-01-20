package country.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import country.model.Continent;



@Repository
@Transactional
public class ContiDAOImpl implements ContiDAO{ 
	
	@Autowired
    SessionFactory sessionFactory;
	
	@Override
    public Continent getContinentByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Continent c where c.code = :code");
        query.setString("code",code);
        return (Continent) query.uniqueResult();
    }
}