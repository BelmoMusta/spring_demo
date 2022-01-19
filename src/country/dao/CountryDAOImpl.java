package country.dao;

import country.model.Continent;
import country.model.Country;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

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
@Transactional
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Country getByCode(String countryCode) {
		Country country = null;
		Session session = sessionFactory.openSession();
		Query<Country> query =  session.createQuery("from Country where code = :code");
		query.setParameter("code", countryCode);
		country = (Country)query.setMaxResults(1).uniqueResult();
		session.close();
		return country;
	}

	@Override
	public boolean addCountry(Country country, String continentCode) {
		Session session = sessionFactory.openSession();
		Query<Continent> query =  session.createQuery("from Continent where code = :code");
		query.setParameter("code", continentCode);
		Continent continent = (Continent)query.uniqueResult();
		country.setContinent(continent);
		Integer rs = (Integer) session.save(country);
		return true;

	}

	@Override
	public boolean deleteCountry(String countryCode) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Country countryToBeDeleted = this.getByCode(countryCode);
		session.delete(countryToBeDeleted);
		session.getTransaction().commit();
		return true;
	}

	@Override
	public boolean updateCountry(String countryCode, Country newCountryInfos) {
		Country country = this.getByCode(countryCode);
		newCountryInfos.setId(country.getId());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(newCountryInfos);
		session.getTransaction().commit();
		return true;
	}

	@Override
	public List<Country> getCountriesOfContinent(String continentCode) {
		List<Country> countries = null;
		Session session = sessionFactory.openSession();
		Query<Continent> query =  session.createQuery("from Continent where code = :code");
		query.setParameter("code", continentCode);
		Continent continent = query.uniqueResult();
		if(continent == null) {
			return null;
		}
		countries = continent.getCountries();
		return countries;
	}
	
	public void initData() {
		Session session = sessionFactory.openSession();
		
		Continent continent = new Continent();
		continent.setCode("af");
		continent.setName("Africa");
		session.save(continent);
		
		Country country = new Country();
		country.setCode("ma");
		country.setDevise("Dirham");
		country.setGreetings("Salam");
		country.setName("Maroc");
		country.setContinent(continent);
		session.save(country);
		
		country = new Country();
		country.setCode("eg");
		country.setDevise("EP");
		country.setGreetings("Salam");
		country.setName("Egypt");
		country.setContinent(continent);
		session.save(country);
		
		continent = new Continent();
		continent.setCode("eu");
		continent.setName("Europe");
		session.save(continent);
		
		country = new Country();
		country.setCode("fr");
		country.setDevise("Euro");
		country.setGreetings("Bonjour");
		country.setName("France");
		country.setContinent(continent);
		session.save(country);
		
		country = new Country();
		country.setCode("es");
		country.setDevise("Euro");
		country.setGreetings("Hola");
		country.setName("Espagne");
		country.setContinent(continent);
		session.save(country);
		
		country = new Country();
		country.setCode("en");
		country.setDevise("GBP");
		country.setGreetings("Hello");
		country.setName("England");
		country.setContinent(continent);
		session.save(country);
		
		country = new Country();
		country.setCode("de");
		country.setDevise("Euro");
		country.setGreetings("Halo");
		country.setName("Germany");
		country.setContinent(continent);
		session.save(country);

		session.close();

	}
}
