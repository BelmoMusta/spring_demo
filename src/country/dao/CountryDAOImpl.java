package country.dao;

import country.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Logger;
import java.sql.*;
import java.util.*;

@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {

	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;

	/*

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
				Integer continent_identifiant = resultSet.getInt(6);
				
				country.setId(id);
				country.setName(name);
				country.setCode(code);
				country.setDevise(devise);
				country.setGreetings(greetings);
				// country.setContinent(continent_id);
				
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return country;
	}

	*/

	@Override
	public Country getByCode(String countrycode){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Country c where c.code = :code");
		query.setString("code",countrycode);
		return (Country) query.list().get(0);
	}

	@Override
	public void saveCountry(Country country) {
		sessionFactory.getCurrentSession().save(country);
	}

	@Override
	public void deleteCountry(Country country) { sessionFactory.getCurrentSession().delete(country); }

	@Override
	public void updateCountry(Country country) { sessionFactory.getCurrentSession().update(country); }

	@Override
	public List<Country> getAllCountries(String continentCode) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Country where continent.code= :code");
		query.setParameter("code",continentCode);
		return (List<Country>) query.list();
	}
}
