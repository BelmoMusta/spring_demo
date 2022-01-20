package country.dao;

import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Logger;

@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Country getByCode(String countryCode) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Country c where c.code = :countryCode");
		query.setString("countryCode",countryCode);
		return (query.list().isEmpty()) ? null : (Country) query.list().get(0);
	}

	@Override
	public void save(Country country) {
		sessionFactory.getCurrentSession().save(country);
	}
	@Override
	public void deleteCountry(Country country) {
		sessionFactory.getCurrentSession().delete(country);
	}
	@Override
	public void updateCountry(Country country) {
		sessionFactory.getCurrentSession().update(country);
	}
	@Override
	public List<Country> getAllCountries(String codeContinent) {
		List<Country> res;
		Query query = sessionFactory.getCurrentSession().createQuery("from Country where continent.code= :codeContinent");
		query.setParameter("codeContinent", codeContinent);
		res =(List<Country>) query.list();
		return res;
	}
}
