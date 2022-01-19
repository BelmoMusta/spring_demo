package country.dao;

import country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public List<Country> getAllCountries() {
		List<Country> countries = new ArrayList<>();
		Country country = null;
		// TODO Auto-generated method stub
		try {
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM country;");
			while (rs.next()) {
				// Display values
				System.out.print(rs.getInt("id"));
				System.out.print(", " + rs.getString("name"));
				System.out.print(", " + rs.getString("code"));
				System.out.print(", " + rs.getString("devise"));
				System.out.println(", " + rs.getString("greetings"));

				country = new Country();
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String code = rs.getString(3);
				String devise = rs.getString(4);
				String greetings = rs.getString(5);

				country.setId(id);
				country.setName(name);
				country.setCode(code);
				country.setDevise(devise);
				country.setGreetings(greetings);
				countries.add(country);
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return countries;
	}

	public int addCountry(Country country) {
		// TODO Auto-generated method stub
		int rslt = 0;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO country(name, code, devise, greetings) VALUES(?, ?, ?,?);");
			preparedStatement.setString(1, country.getName());
			preparedStatement.setString(2, country.getCode());
			preparedStatement.setString(3, country.getDevise());
			preparedStatement.setString(4, country.getGreetings());
			int resultSet = preparedStatement.executeUpdate();
			return resultSet;
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return rslt;
	}

	public int removeCountry(String countryCode) {
		// TODO Auto-generated method stub
		int rslt = 0;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM country WHERE code = ?;");
			preparedStatement.setString(1, countryCode);
			int resultSet = preparedStatement.executeUpdate();
			return resultSet;
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return rslt;
	}

}
