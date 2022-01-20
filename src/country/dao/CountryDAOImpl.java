package country.dao;

import country.model.Country;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
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
	public void addElement(String[] countryInfos) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO country (name, code, devise, greetings, ccode) VALUES (?, ?, ?, ?, ?);");
			for(int i = 1; i <= 5; i++) {
				try {
					preparedStatement.setString(i, countryInfos[i - 1]);
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", e);
		}
	}
	
	@Override
	public void deleteElement(String code) {
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
	public void updateElement(String code) {
		Scanner inputFromConsole = new Scanner(System.in);
		String Ninfos = inputFromConsole.next();
		String[] countryNInfos = Ninfos.split(",", 5);
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE country SET name = ?, code = ?, devise = ?, greetings = ?, ccode = ? WHERE code = ?;");
			for(int i = 1; i <= 5; i++) {
				try {
					preparedStatement.setString(i, countryNInfos[i - 1]);
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			preparedStatement.setString(6, code);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
	}
	
	public String getContinentByCode(String continentCode) {
		String res = "";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT cname FROM continent WHERE ccode = ?;");
			preparedStatement.setString(1, continentCode);
            ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				res = resultSet.getString(1);
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		
		return res;
	}
	
	@Override
	public List<String> getContinentCountries(String continentCode){
		System.out.println("Les pays du continent " + getContinentByCode(continentCode) + " sont : "); 
		
		List<String> l = new ArrayList<String>();
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM country WHERE ccode = ?;");
			preparedStatement.setString(1, continentCode);
            ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				l.add(resultSet.getString(1));
			}
			
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		
		return l;
	}
	
	@Override
	public boolean checkExistance(String code) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country WHERE code = ?;");
			preparedStatement.setString(1, code);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) return true;
			
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		
		return false;
	}
	
	@Override
	public boolean checkContinentExistance(String Ccode) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM continent WHERE ccode = ?;");
			preparedStatement.setString(1, Ccode);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) return true;
			
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		
		return false;
	}
	
}
