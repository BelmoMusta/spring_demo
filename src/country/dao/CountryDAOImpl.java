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
	public Country getByCode(String Code) {
		Session s = getSession();
		Transaction t = s.beginTransaction();
		String str = "FROM Country where code=:countryCode";
		List<Country> countries = s.createQuery(str).setParameter("countryCode", Code).list();
		for (Iterator iterator = countries.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
		}
		t.commit();
		s.close();

		return countries.get(0);
	}

	@Override
	public void addCountry(Country c) {

		persist(c);

	}

	@Override
	public void deleteCountry(String Code) {
		Session s = getSession();
		Transaction t = s.beginTransaction();
		String str = "delete from Country where code = :codeID";
		int rslt = s.createSQLQuery(str).setParameter("codeID", Code).executeUpdate();
		t.commit();
		s.close();

	}

	@Override
	public List<Country> getCountries() {
		Session s = getSession();
		Transaction t = s.beginTransaction();
		String str = "FROM Country";
		List<Country> countries = s.createQuery(str).list();
		for (Iterator iterator = countries.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
		}
		t.commit();
		s.close();
		return countries;
	}

	@Override
	public List<Continent> getAllCountinents() {
		Session s = getSession();
		Transaction t = s.beginTransaction();
		String str = "FROM continent";
		List<Continent> list = s.createQuery(str).list();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Country c = (Country) iterator.next();
		}
		t.commit();
		s.close();
		return list;
	}

	@Override
	public void updateCountry(String Code, Country c) {
		Session s = getSession();
		Transaction t = s.beginTransaction();
		String str = "UPDATE Country SET code = :code ,name = :name ,continent = :continent ,devise =:devise ,greetings=:greetings  WHERE code = :codeID";
		int rslt = s.createSQLQuery(str).setParameter("codeID", Code)
				.setParameter("code", c.getCode()).setParameter("name", c.getName())
				.setParameter("continent", c.getContinent())
				.setParameter("devise", c.getDevise()).setParameter("greetings", c.getGreetings())
				.executeUpdate();
		t.commit();
		s.close();

	}

	@Override
	public List<Country> getCountriesByContinent(String Continent) {
		Session s = getSession();
		Transaction t = s.beginTransaction();
		String str = "FROM Country where continent=:continentCode";
		List<Country> countryList = s.createQuery(str).setParameter("continentCode", Continent).list();
		for (Iterator iterator = countryList.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
		}
		t.commit();
		return countryList;
	}

}
