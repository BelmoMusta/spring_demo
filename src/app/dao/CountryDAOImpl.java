package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import app.entity.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Country getByCode(String countryCode) {
		String hql = "from Country C where C.code = ?1";
		Query<?> query = this.getSessionFactory().createQuery(hql);
		query.setParameter(1, countryCode);
		return (Country) query.uniqueResult();
	}

	@Override
	public Country addCountry(Country country) {
		try (Connection connection = dataSource.getConnection();) {

			try (PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO  country(code,name,devise,greetings) VALUES(?,?,?,?);");) {
				preparedStatement.setString(1, country.getCode());
				preparedStatement.setString(2, country.getName());
				preparedStatement.setString(3, country.getDevise());
				preparedStatement.setString(4, country.getGreetings());
				
				preparedStatement.executeUpdate();

			}

		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return country;
	}

	@Override
	public Country getCountry(String code) {

		return this.getByCode(code);
	}

	@Override
	public List<Country> getCountries() {
		
		return hibernateTemplate.loadAll(Country.class);
	}

	@Override
	public void deleteCountry(String code) {
		String hql = "DELETE FROM Country where code = ?1";
		Query<?> query = this.getSessionFactory().createQuery(hql);
		query.setParameter(1, code);
		query.executeUpdate();
	}

	@Override
	public Country updateCountry(Country country, String countryCode) {
		String hql = "UPDATE  Country SET name = ?1 , devise = ?2, greetings = ?3  where code = ?4";
		Query<?> query = this.getSessionFactory().createQuery(hql);
		query.setParameter(1, country.getName());
		query.setParameter(2, country.getDevise());
		query.setParameter(3, country.getGreetings());
		query.setParameter(4, countryCode);	
		query.executeUpdate();
		return country;
	}

	@Override
	public List<Country> getCountriesByContinent(String continent) {
		
		return null;
	}

	private final Session getSessionFactory() {
		return sessionFactory.getCurrentSession();
	}
}
