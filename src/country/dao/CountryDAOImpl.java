package country.dao;

import country.model.Country;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ImportResource(locations = {"/beans/country-context.xml","/beans/database-config.xml","/beans/hibernate-config.xml"})
@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Country getByCode(String countryCode) {
		Country country = null;
		try {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			Query<Country> query = session.createQuery("from country where code = :code");
			query.setParameter("code", countryCode);
			country =(Country)query.uniqueResult();
			session.getTransaction().commit();
			session.close();			
		} catch (HibernateException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		System.out.println(countryCode);
		System.out.println(country);
		return country;
	}

	@Override
	public Country saveCountry(Country c) {
		Transaction tx =null;
		try(Session session = getSessionFactory().openSession()){
			
			tx = session.beginTransaction();
			session.save(c);		
			session.getTransaction().commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}throw e;
		}
		Country country = this.getByCode(c.getCode());
		return country;	
	}

	@Override
	public void deleteCountry(String deleteCode) {		
	
		try {
			Session session = sessionFactory.openSession();		
			session.beginTransaction();
			session.delete(this.getByCode(deleteCode));
			session.getTransaction().commit();
			session.close();			
		} catch (HibernateException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		
	}

	@Override
	public Country updateCountry(Country c, String code) {
		try {
			Session session = sessionFactory.openSession();	
			session.beginTransaction();
			c.setId(this.getByCode(code).getId());
			session.update(c);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return c;
	}

	@Override
	public List<Country> continentCountries(String code) {
		List<Country> l = new ArrayList<>();		
		try {
			Session session = sessionFactory.openSession();	
			session.beginTransaction();
			Query<Country> query = session.createQuery("from country WHERE code_continent = :code");
			query.setParameter("code", code);
			l =query.getResultList();			
			session.getTransaction().commit();
			session.close();
			
		} catch (HibernateException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return l;
	}
	
}
