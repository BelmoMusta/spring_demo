package country.dao;

import country.model.Continent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Repository
@Transactional
public class ContinentDAOImpl implements ContinentDAO {
	private static final Logger LOGGER = Logger.getLogger(ContinentDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean continentExists(String continentCode) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Continent c where c.code = :code");
		query.setString("code",continentCode);
		return (query.uniqueResult() != null);
	}

	@Override
	public Continent getByCode(String continentCode){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Continent c where c.code = :code");
		query.setString("code",continentCode);
		return (Continent) query.uniqueResult();
	}
}
