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
	public void addCountry(Country c) {
		 
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO country(name,code,devise,greetings) VALUES(? , ? , ? , ?);");
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getCode());
			preparedStatement.setString(3, c.getDevise());
			preparedStatement.setString(4, c.getGreetings());
			preparedStatement.executeUpdate();
		
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
	}

	@Override
	public void deleteCountry(String Code) {
		@SuppressWarnings("unused")
		Country country = null;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM country WHERE code = ?;");
			preparedStatement.setString(1, Code);
			preparedStatement.executeUpdate();
			
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		
	}

	@Override
	public void getAllCountries() {
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
	      }

	    } catch (SQLException exception) {
	      LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
	    }
	  }

	@Override
	public void updateCountry(String Code , Country c) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE country SET code=?,name=?,devise=?,greetings=? where code= ? ; ");
			preparedStatement.setString(1, c.getCode());
			preparedStatement.setString(2, c.getName());
			preparedStatement.setString(3, c.getDevise());
			preparedStatement.setString(4, c.getGreetings());
			preparedStatement.setString(4, c.getGreetings());
			preparedStatement.setString(5, Code);
			preparedStatement.executeUpdate();
		
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
	}
	
	
}
