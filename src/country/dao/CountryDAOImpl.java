package country.dao;


import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import country.model.Continent;
import country.model.Country;


@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {
	
	@Autowired
    SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	ContinentDAO continentDAO;
	
	@Override
	public Country getByCode(String code) {
		//Get session and Begin transaction
		Session session = getSessionFactory().openSession();
    	session.beginTransaction();
		
    	//Query
		Query query = session.createQuery("from Country where code = :code");
		query.setParameter("code", code);
		Country country=(Country) query.uniqueResult();
		
		//Commit transaction and close session
		session.getTransaction().commit();
		session.close();
		
		//return the Result
        return country;
	}

	@Override
	public void addCountry(Country country,String nomContinet) {
		country.setContinent(continentDAO.getContientByName(nomContinet));
		
		//Get session and Begin transaction
        Session session=getSessionFactory().openSession();
        session.beginTransaction();
        
        Query query = session.createSQLQuery("INSERT INTO Country(name, code, devise, greetings,continent_id) VALUES(:name, :code, :devise,:greeting,:continent);");
        query.setParameter("name",country.getName())
        .setParameter("code",country.getCode())
        .setParameter("devise",country.getDevise())
        .setParameter("greeting",country.getGreetings())
        .setParameter("continent",country.getContinent());
        query.executeUpdate();
        
		//Commit transaction and close session
        session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteCountry(String code) {
		//Get session and Begin transaction
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		Country country = getByCode(code);
		session.delete(country);
		
		//Commit transaction and close session
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void updateCountry(String code, Country country, String nomContinet) {
		country.setContinent(continentDAO.getContientByName(nomContinet));
		
		//Get session and Begin transaction
        Session session=getSessionFactory().openSession();
        session.beginTransaction();
        
    	Query query = session.createQuery("update Country c set c.name=:name,c.code=:code,c.devise=:devise,c.greetings=:greeting,c.continent=:continent where c.code=:codeUpdate");
    	 query.setParameter("name",country.getName())
    	      .setParameter("code",country.getCode())
    	      .setParameter("devise",country.getDevise())
    	      .setParameter("greeting",country.getGreetings())
    	      .setParameter("continent",country.getContinent())
    	      .setParameter("codeUpdate", code);
    	query.executeUpdate();
        
		//Commit transaction and close session
        session.getTransaction().commit();
		session.close();
		
	}
	
	@Override
	public boolean exist(String code) {
		int result;
		//Get session and Begin transaction
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		//Query
		Query query = session.createQuery("from Country c where c.code = :code");
		query.setParameter("code",code);
		result = query.list().size();
		
		//Commit transaction and close session
		session.getTransaction().commit();
		session.close();
		
		return  result > 0;
	}
	
	
	
}
