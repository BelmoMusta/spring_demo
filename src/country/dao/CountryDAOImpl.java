package country.dao;

import country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public boolean addCountry(Country country) {
		String INSERT_SQL = "INSERT INTO country(code, name, devise, greetings) VALUES(?, ?, ?, ?);";
		int numRowAffected = 0;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
			preparedStatement.setString(1, country.getCode());
			preparedStatement.setString(2, country.getName());
			preparedStatement.setString(3, country.getDevise());
			preparedStatement.setString(4, country.getGreetings());
			numRowAffected = preparedStatement.executeUpdate();
			
		}catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return numRowAffected == 1 ? true : false;
	}

	@Override
	public boolean deleteCountry(String countryCode) {
		String DELETE_SQL = "DELETE FROM country WHERE code = ?;";
		int numRowAffected = 0;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
			preparedStatement.setString(1, countryCode);
			numRowAffected = preparedStatement.executeUpdate();
			
		}catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return numRowAffected == 1 ? true : false;
	}

	@Override
	public boolean updateCountry(String countryCode, Country newCountryInfos) {
		String UPDATE_SQL = "UPDATE country SET code = ?, name = ?, devise = ?, greetings = ? WHERE code = ?;";
		int numRowAffected = 0;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
			preparedStatement.setString(1, newCountryInfos.getCode());
			preparedStatement.setString(2, newCountryInfos.getName());
			preparedStatement.setString(3, newCountryInfos.getDevise());
			preparedStatement.setString(4, newCountryInfos.getGreetings());
			preparedStatement.setString(5, countryCode);
			numRowAffected = preparedStatement.executeUpdate();
			
		}catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return numRowAffected == 1 ? true : false;
	}
}
