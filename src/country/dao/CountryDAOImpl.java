package country.dao;

import country.model.Country;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
	@Autowired
	SessionFactory sessionFactory;

	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());

	@Override
	public Country getByCode(String countryCode) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Country c where c.code = :code");
		query.setString("code", countryCode);
		return (Country) query.uniqueResult();
	}

	@Override
	public void save(Country country) {
		sessionFactory.getCurrentSession().save(country);
	}

	@Override
	public void getAllCountries() {
		List<Country> countries = sessionFactory.getCurrentSession().createQuery("from Country").list();
		countries.forEach(System.out::println);

	}
}
