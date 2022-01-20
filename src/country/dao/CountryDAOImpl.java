package country.dao;

import country.model.Country;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;
	Session session = sessionFactory.openSession() ; 

	
	@Override
	public Country getByCode(String countryCode) {
		Country result;

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Country c where c.code = :code");
		query.setString("code",countryCode);
		result = (Country) query.uniqueResult();
		
		return result;
	}
	
	@Override
	public void addCountry(Country country) {
		session =sessionFactory.openSession();
		session.beginTransaction();
		session.save(country);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override

	public Country modifyCountry(String code,Country c) {
		Country result;
	    session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("modify Country c set c.name=:name,c.code=:code,c.devise=:devise,c.greetings=:greeting where c.code=:codeToModify\"");

		query.setParameter("name",c.getName())
		      .setParameter("code",c.getCode())
		      .setParameter("devise",c.getDevise())
		      .setParameter("greeting",c.getGreetings())
		      .setParameter("codeToModify", code);
		result = (Country) query.uniqueResult();
		
		return result;
		
	}
	
	@Override
	public void deleteCountry(String code) {
		
		    Country result;
		
			session =sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Country where code = :code");
			query.setParameter("code", code);
			result = (Country) query.uniqueResult();
			session.delete(result);
			
			
			session.getTransaction().commit();
			session.close();
		}
}
