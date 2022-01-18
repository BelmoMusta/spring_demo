package country.dao;

import country.model.Country;
<<<<<<< HEAD
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
=======


import org.hibernate.Session;
import org.hibernate.Transaction;

import org.springframework.stereotype.Repository;



import java.util.Iterator;
import java.util.List;

@Repository("countryDAO")
public class CountryDAOImpl extends AbstractCountryDAO implements CountryDAO  {

	
	
	
	@Override
	public void enregistrerCountry(Country country) 
	{
		persist(country);
		
	}
	
	@Override
	public void listCountry() 
	{
		Session session = getSession();
	    Transaction tran = session.beginTransaction();
		String str="FROM Country";
		List<Country> countries = session.createQuery(str).list();
		for (Iterator iterator =countries.iterator(); iterator.hasNext();){
			Country country = (Country) iterator.next();
			System.out.print(" Nom: " + country.getName());
			System.out.print(" ,Devise: " + country.getDevise());
			System.out.print(" ,Greetings: " + country.getGreetings());
			System.out.println(",Code: " + country.getCode());
	}}
	

	

	
>>>>>>> origin/aspect-fonctionnel-01
}
