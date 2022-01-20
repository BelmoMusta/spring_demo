package country.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import country.model.Continent;
import country.model.Country;

@Repository
public class ContinentDAOImpl implements ContinentDAO{
	
	@Autowired
    SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Continent getContientByName(String name) {
		Continent continent;
		//Get session and Begin transaction
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Continent c where c.name = :name");
        query.setParameter("name",name);
        continent = (Continent) query.list().get(0);
		
		//Commit transaction and close session
		session.getTransaction().commit();
		session.close();
        return continent;
	}
	
	@Override
	public Continent getContinentByCode(String code) {
		Continent continent;
		//Get session and Begin transaction
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		//Query
		Query query = session.createQuery("from Continent C where C.code =:code");
		query.setParameter("code", code);
		continent = (Continent) query.uniqueResult();
		
		//Commit transaction and close session
		session.getTransaction().commit();
		session.close();
		
		return continent;
	}
	
	@Override
	public List<Country> getCountriesByContinentCode(String code) {
		List<Country> l;
		
		//Get session and Begin transaction
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		Continent continent = getContinentByCode(code);
		Query query = session.createQuery("from Country c where c.continent =: continent");
		query.setParameter("continent",continent);
		l =  query.getResultList();
		
		//Commit transaction and close session
		session.getTransaction().commit();
		session.close();
		
		return l;
	}
	@Override
	public boolean exist(String nomContinent) {
		boolean result;
		//Get session and Begin transaction
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Continent c where c.name = :name");
		query.setParameter("name",nomContinent);
		result = query.list().size() > 0;
		
		//Commit transaction and close session
		session.getTransaction().commit();
		session.close();
		
		return result;
	}
}
