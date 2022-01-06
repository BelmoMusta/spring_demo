package country.dao;

import country.model.Continent;
import country.model.Country;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Country getByCode(String countryCode) {
		Country country = null;
		Session session = sessionFactory.openSession();
		Query<Country> query =  session.createQuery("from Country where code = :code");
		query.setParameter("code", countryCode);
		country = (Country)query.uniqueResult();
		session.close();
		return country;
		
//		try {
//			Connection connection = dataSource.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country where code = ?;");
//			preparedStatement.setString(1, countryCode);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			
//			if (resultSet.next()) {
//				country = new Country();
//				Integer id = resultSet.getInt(1);
//				String name = resultSet.getString(2);
//				String code = resultSet.getString(3);
//				String devise = resultSet.getString(4);
//				String greetings = resultSet.getString(5);
//				
//				country.setId(id);
//				country.setName(name);
//				country.setCode(code);
//				country.setDevise(devise);
//				country.setGreetings(greetings);
//				
//			}
//		} catch (SQLException exception) {
//			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
//		}
	}

	@Override
	public boolean addCountry(Country country) {
		Session session = sessionFactory.openSession();
		Integer rs = (Integer) session.save(country);
		return true;

	}

	@Override
	public boolean deleteCountry(String countryCode) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Country countryToBeDeleted = this.getByCode(countryCode);
		System.out.println(countryToBeDeleted);
		session.delete(countryToBeDeleted);
		session.getTransaction().commit();
		return true;
	}

	@Override
	public boolean updateCountry(String countryCode, Country newCountryInfos) {
//		String UPDATE_SQL = "UPDATE country SET code = ?, name = ?, devise = ?, greetings = ? WHERE code = ?;";
//		int numRowAffected = 0;
//		try {
//			Connection connection = dataSource.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
//			preparedStatement.setString(1, newCountryInfos.getCode());
//			preparedStatement.setString(2, newCountryInfos.getName());
//			preparedStatement.setString(3, newCountryInfos.getDevise());
//			preparedStatement.setString(4, newCountryInfos.getGreetings());
//			preparedStatement.setString(5, countryCode);
//			numRowAffected = preparedStatement.executeUpdate();
//			
//		}catch (SQLException exception) {
//			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
//		}
//		return numRowAffected == 1 ? true : false;
		Country country = this.getByCode(countryCode);
		newCountryInfos.setId(country.getId());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(newCountryInfos);
		session.getTransaction().commit();
		return true;
	}

	@Override
	public List<Country> getCountriesOfContinent(String continentCode) {
		List<Country> countries = null;
		Session session = sessionFactory.openSession();
		Query<Continent> query =  session.createQuery("from Continent where code = :code");
		query.setParameter("code", continentCode);
		countries = query.uniqueResult().getCountries();
		return countries;
	}
}
