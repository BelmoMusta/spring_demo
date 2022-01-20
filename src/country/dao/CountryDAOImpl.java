package country.dao;

import country.model.Country;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
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
	}

	@Override
	public Country addCountry(String nom, String code, String devise, String greeting) {
		Country c = new Country();
		c.setName(nom);;
		c.setCode(code);
		c.setDevise(devise);
		c.setGreetings(greeting);
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO country (name, code, devise, greetings) VALUES (?,?,?,?);");
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, code);
			preparedStatement.setString(3, devise);
			preparedStatement.setString(4, greeting);

			int i = preparedStatement.executeUpdate();
			
			if(i>0) {
				System.out.println("success");
			} else {
				System.out.println("failed");
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return c;
	}

	@Override
	public void deleteCountry(String code) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE from country where code=?;");
			preparedStatement.setString(1, code);

			int i = preparedStatement.executeUpdate();
			
			if(i>0) {
				System.out.println("success");
			} else {
				System.out.println("failed");
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
	}

	@Override
	public void updateCountry(String code, String nom, String devise, String greeting) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("update country set name=?, devise=?, greetings=? where code=?;");
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, devise);
			preparedStatement.setString(3, greeting);
			preparedStatement.setString(4, code);

			int i = preparedStatement.executeUpdate();
			
			if(i>0) {
				System.out.println("success");
			} else {
				System.out.println("failed");
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
	}

	@Override
	public List<Country> listAllCountries(String codeContinent) {
		List<Country> countries = null;
		
		
		
		return countries;
	}

	@Override
	public List<Country> listAll() {
		List<Country> c = new ArrayList<Country>();
		Country country = null;
		ResultSet resultSet = null;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country;");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
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
				
				c.add(country);
				
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return c;
	}
}
