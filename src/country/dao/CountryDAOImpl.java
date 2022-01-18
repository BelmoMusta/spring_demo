package country.dao;

import country.model.Country;
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
				String continent = resultSet.getString(4);
				String devise = resultSet.getString(5);
				String greetings = resultSet.getString(6);

				country.setId(id);
				country.setName(name);
				country.setCode(code);
				country.setContinent(continent);
				country.setDevise(devise);
				country.setGreetings(greetings);

			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return country;
	}

	@Override
	public List<Country> getAllCountriesInContinent(String continentCode) {
		// TODO Auto-generated method stub
		List<Country> countryList = new ArrayList<>();
		Country country;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM country where continent = ?;");
			preparedStatement.setString(1, continentCode);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				country = new Country();
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String code = resultSet.getString(3);
				String continent = resultSet.getString(4);
				String devise = resultSet.getString(5);
				String greetings = resultSet.getString(6);

				country.setId(id);
				country.setName(name);
				country.setCode(code);
				country.setContinent(continent);
				country.setDevise(devise);
				country.setGreetings(greetings);

				countryList.add(country);

			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return countryList;
	}

	@Override
	public String getContinentName(String continentCode) {
		// TODO Auto-generated method stub
		String continentName = "";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT name FROM continent where code = ?;");
			preparedStatement.setString(1, continentCode);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				continentName = resultSet.getString(1);
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return continentName;
	}
}
