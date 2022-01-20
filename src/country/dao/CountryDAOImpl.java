package country.dao;

import country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class CountryDAOImpl extends AbstractCountryDAO implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private DataSource dataSource;

	@Override
	public Country getCountryByCode(String countryCode) {
		Session session2 = getSession();
		Transaction trans = session2.beginTransaction();
		String str1 = "FROM Country where code=:countryCode";
		List<Country> countries = session2.createQuery(str1).setParameter("countryCode", countryCode).list();
		for (Iterator iterator = countries.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
			System.out.print(" Nom: " + country.getName());
			System.out.print(" ,Devise: " + country.getDevise());
			System.out.print(" ,Greetings: " + country.getGreetings());
			System.out.println(",Code: " + country.getCode());
		}
		trans.commit();
		session2.close();

		return countries.get(0);
	}

	@Override
	public List<Country> getAllCountries() {
		Session session1 = getSession();
		Transaction tran = session1.beginTransaction();
		String str = "FROM Country";
		List<Country> countries = session1.createQuery(str).list();
		for (Iterator iterator = countries.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
			System.out.print(" Nom: " + country.getName());
			System.out.print(" ,Devise: " + country.getDevise());
			System.out.print(" ,Greetings: " + country.getGreetings());
			System.out.println(",Code: " + country.getCode());
		}
		tran.commit();
		session1.close();
		return countries;
	}

	public int addCountry(Country country) {
		// TODO Auto-generated method stub
		persist(country);

		return 0;
	}

	public int removeCountry(String countryCode) {
		// TODO Auto-generated method stub
		Session session3 = getSession();
		Transaction trans2 = session3.beginTransaction();
		String str2 = "delete from Country where code = :codeID";
		int query = session3.createSQLQuery(str2).setParameter("codeID", countryCode).executeUpdate();
		trans2.commit();
		session3.close();
		return 0;
	}

	public int updateByCode(String countryCode, Country country) {
		// TODO Auto-generated method stub
		Session session4 = getSession();
		Transaction trans3 = session4.beginTransaction();
		String str3 = "UPDATE Country SET code = :code ,name = :name ,continent = :continent ,devise =:devise ,greetings=:greetings  WHERE code = :codeID";
		int query = session4.createSQLQuery(str3).setParameter("codeID", countryCode)
				.setParameter("code", country.getCode()).setParameter("name", country.getName())
				.setParameter("continent", country.getContinent())
				.setParameter("devise", country.getDevise()).setParameter("greetings", country.getGreetings())
				.executeUpdate();
		trans3.commit();
		session4.close();
		return 0;
	}

	public List<Country> getAllCountriesInContinent(String continentCode) {
		// TODO Auto-generated method stub
		Session session2 = getSession();
		Transaction trans = session2.beginTransaction();
		String str1 = "FROM Country where continent=:continentCode";
		List<Country> countryList = session2.createQuery(str1).setParameter("continentCode", continentCode).list();
		for (Iterator iterator = countryList.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
			System.out.println("Code: " + country.getCode());
			System.out.print(" ,Nom: " + country.getName());
			System.out.println(",continent: " + country.getContinent());
			System.out.print(" ,Devise: " + country.getDevise());
			System.out.print(" ,Greetings: " + country.getGreetings());
		}
		trans.commit();
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