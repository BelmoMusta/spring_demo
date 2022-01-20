package country.dao;

import country.model.Continent;
import country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.sql.DataSource;
import java.util.logging.Logger;

@Repository
public class CountryDAOImpl extends AbstractCountryDAO implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private DataSource dataSource;

	@Override
	public Country getCountryByCode(String countryCode) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		String query = "FROM Country where code=:countryCode";
		List<Country> countries = session.createQuery(query).setParameter("countryCode", countryCode).list();
		for (Iterator iterator = countries.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
		}
		transaction.commit();
		session.close();
		if (countries.size() == 0)
			return null;
		return countries.get(0);
	}

	@Override
	public List<Country> getAllCountries() {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		String query = "FROM Country";
		List<Country> countries = session.createQuery(query).list();
		for (Iterator iterator = countries.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
		}
		transaction.commit();
		session.close();
		return countries;
	}

	public int addCountry(Country country) {
		persist(country);

		return 0;
	}

	public int removeCountry(String countryCode) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		String query = "delete from Country where code = :codeID";
		int rslt = session.createSQLQuery(query).setParameter("codeID", countryCode).executeUpdate();
		transaction.commit();
		session.close();
		return rslt;
	}

	public int updateByCode(String countryCode, Country country) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		String query = "UPDATE Country SET code = :code ,name = :name ,continent = :continent ,devise =:devise ,greetings=:greetings  WHERE code = :codeID";
		int rslt = session.createSQLQuery(query).setParameter("codeID", countryCode)
				.setParameter("code", country.getCode()).setParameter("name", country.getName())
				.setParameter("continent", country.getContinent())
				.setParameter("devise", country.getDevise()).setParameter("greetings", country.getGreetings())
				.executeUpdate();
		transaction.commit();
		session.close();
		return rslt;
	}

	public List<Country> getAllCountriesInContinent(String continentCode) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		String query = "FROM Country where continent=:continentCode";
		List<Country> countryList = session.createQuery(query).setParameter("continentCode", continentCode).list();
		for (Iterator iterator = countryList.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
		}
		transaction.commit();
		return countryList;
	}

	@Override
	public String getContinentName(String continentCode) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		String query = "FROM Continent where code=:continentCode";
		List<Continent> continents = session.createQuery(query).setParameter("continentCode", continentCode).list();
		for (Iterator iterator = continents.iterator(); iterator.hasNext();) {
			Continent continent = (Continent) iterator.next();
		}
		transaction.commit();
		session.close();

		return continents.get(0).getName();
	}
}