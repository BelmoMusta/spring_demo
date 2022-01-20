package country.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import country.domain.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//@ImportResource(locations = {"/beans/country-context.xml","/beans/database-config.xml","/beans/hibernate-config.xml"})
@ComponentScan(basePackages = "country")
@Repository
@Transactional
public class CountryDAOImpl implements ICountryDao {
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
		return country;
	}

	@Override
	public Country saveCountry(Country country) {
		Transaction transaction =null;
		String etas="";
		try{
			Session session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(country);		
			session.getTransaction().commit();
			etas="done";
		}catch(Exception e) {
			etas="error";
			if(transaction != null) {
				transaction.rollback();
			}throw e;
		}
		System.out.println(etas);
		Country countryReturn = this.getByCode(country.getCode());
		return countryReturn;	
	}

	@Override
	public void deleteCountry(Country country) {		
	
		try {
			Session session = sessionFactory.openSession();		
			session.beginTransaction();
			session.delete(country);
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
			Query<Country> query = session.createQuery("from country WHERE continent = :code");
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
