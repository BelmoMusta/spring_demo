package country.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import country.model.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private DataSource dataSource;

	@Override
	public Country getByCode(String countryCode) {
		Country country = null;
		try (Connection connection = dataSource.getConnection();) {

			try (PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM country where code = ?;");) {
				preparedStatement.setString(1, countryCode);
				try (ResultSet resultSet = preparedStatement.executeQuery();) {
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
				}

			}

		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return country;
	}

	@Override
	public Country addCountry(Country country) {
		try (Connection connection = dataSource.getConnection();) {

			try (PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO country (name,code,devise,greetings) VALEUS (?,?,?,?);");) {
				preparedStatement.setString(1, country.getName());
				preparedStatement.setString(2, country.getCode());
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
		List<Country> countries = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();) {

			try (Statement statement = connection.createStatement();) {

				try (ResultSet resultSet = statement.executeQuery("SELECT * FROM country ;");) {
					while (resultSet.next()) {
						Country country = new Country();
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

						countries.add(country);

					}
				}

			}

		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return countries;
	}

	@Override
	public void deleteCountry(String code) {
		try (Connection connection = dataSource.getConnection();) {

			try (PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM country where code = ?;");) {
				preparedStatement.setString(1, code);
				preparedStatement.executeUpdate();
			}

		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
	}

	@Override
	public Country updateCountry(Country country , String countryCode) {
		
		try (Connection connection = dataSource.getConnection();) {

			try (PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE  country SET name = ? , devise = ? greetings = ?  where code = ?;");) {
				preparedStatement.setString(1, country.getName());
				preparedStatement.setString(2, country.getDevise());
				preparedStatement.setString(3, country.getGreetings());
				preparedStatement.setString(4, countryCode);
				
				preparedStatement.executeUpdate();

			}

		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return country;
	}

	@Override
	public List<Country> getCountriesByContinent(String continent) {
		
		return null;
	}
}
