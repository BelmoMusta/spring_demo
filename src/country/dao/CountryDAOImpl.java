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

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		country = (Country)query.uniqueResult();
		session.close();
		return country;
	}

	@Override
	public boolean addCountry(Country country) {
		Session session = sessionFactory.openSession();
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
		countries = query.uniqueResult().getCountries();
		return countries;
	}
}
