package country.dao;

import country.model.Country;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	//SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session ; 
	
	//=sessionFactory.openSession();
	//session.beginTransaction();
	//session.getTransaction().commit();
	//session=sessionFactory.openSession();
	//session.close();
	
	
	@Override
	public Country getByCode(String code) {
		Country result;
		
		session =sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Country where code = :code");
		query.setParameter("code", code);
		result = (Country) query.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return result;
	}

	@Override
	public void addCountry(Country country) {
		session =sessionFactory.openSession();
		session.beginTransaction();
		session.save(country);
		List result = session.createQuery("from Country").list();
		for (Country event : (List<Country>) result) {System.out.println(event.getName());}
		session.getTransaction().commit();
		session.close();
	}
	
	/*private SessionFactory sessionFactory;

	@Autowired
	public CountryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Country getByCode(String code) {

		Query query = this.getCurrentSession().createQuery("from country where code = ?");
		query.setString("code", code);
		return (Country) query.uniqueResult();

	}

	/*@Autowired
	private DataSource dataSource;
	@Override
	public Country getByCode(String countryCode) {
		Country country = null;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country where code = ?;");
			preparedStatement.setString(1, countryCode);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				country = new Country();
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String code = resultSet.getString(3);
				String devise = resultSet.getString(4);
				String greetings = resultSet.getString(5);
				
				country.setId(id);
				country.setName(name);
				country.setCode(code);
				country.setDevise(devise);
				country.setGreetings(greetings);
				
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return country;
	}*/
	
	
}
