package country.dao;

import country.model.Country;
import java.util.Scanner;
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
				String continentCode = resultSet.getString(6);

				country.setId(id);
				country.setName(name);
				country.setCode(code);
				country.setDevise(devise);
				country.setGreetings(greetings);
				country.setContinentCode(continentCode);

			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return country;
	}

	@Override
	public void addNewCountry(String[] countryInformations) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO country (name, code, devise, greetings, continentcode) VALUES (?, ?, ?, ?, ?);");
			int i = 1;
			while (i <= 5) {
				preparedStatement.setString(i, countryInformations[i - 1]);
				i++;
			}
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", e);
		}

	}

	@Override
	public void deleteCountry(String code) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE country WHERE code = ?;");
			preparedStatement.setString(1, code);
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}

	}

	@Override
	public void modifCountry(String code,String newInfos) {
		String[] countryInfos = newInfos.split(",", 5);
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE country SET name = ?, code = ?, devise = ?, greetings = ?, continentcode = ? WHERE code = ?;");
			int i = 1;
			while (i <= 5) {
				preparedStatement.setString(i, countryInfos[i - 1]);
				i++;
			}
			preparedStatement.setString(6, code);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}

	}

	@Override
	public List<String> continentCountries(String code) {
		List<String> contries = new ArrayList<String>();
		try {
			String continentname = "";
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT continentname FROM continents WHERE continentcode = ?;");
			preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				continentname = resultSet.getString(1);
			}
			System.out.println("les pays existent dans " +continentname + " sont : \n"); 
			
			preparedStatement = connection.prepareStatement("SELECT name FROM country WHERE continentcode = ?;");
			preparedStatement.setString(1, code);
            resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				contries.add(resultSet.getString(1));
			}
	}
		catch (SQLException exception) {
		LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
	}
		return contries;
	}

}
