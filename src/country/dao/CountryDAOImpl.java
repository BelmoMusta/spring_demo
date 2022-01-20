package country.dao;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import country.model.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {

		return sessionFactory.getCurrentSession();
	}

	@Override
	public Country getByCode(String countryCode) {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		String str1 = "FROM Country where code=:countrCode";
		Query query = session.createQuery(str1).setParameter("countrCode", countryCode);
		query.setMaxResults(1);
		Country countrie = (Country) query.uniqueResult();
		trans.commit();
		session.close();
		return countrie;
	}

	@Override
	public Integer AddNewCountry(Country country) {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		Integer NumId = (Integer) session.save(country);
		trans.commit();
		return NumId;

	}
}
