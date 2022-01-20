/*package country.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import country.model.Continent;

@Repository
@Transactional
public class ContinentDAOImpl implements ContinentDAO{
	
	@Autowired
    SessionFactory sessionFactory;

	@Override
	public Continent getByCode(String code) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from continent where code = :code");
		q.setString("code", code);
		
		return (Continent) q.list().get(0);
	}

}
*/