package country.dao;

import java.util.List;
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
		String requet = "FROM Country where code=:paysCode";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(requet).setParameter("paysCode", countryCode);
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

	@Override
	public Integer DeleteCountry(String code) {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		String requet = "delete from Country where code = :paysCode";
		int query = session.createSQLQuery(requet).setParameter("paysCode", code).executeUpdate();
		trans.commit();
		session.close();
		return query;
	}

	@Override
	public Integer EditInfos(String code, Country country) {
		Session session4 = getSession();
		Transaction trans3 = session4.beginTransaction();
		String str3 = "UPDATE Country SET name = :name ,devise =:devise ,greetings=:greetings  WHERE code = :Payscode";
		Integer query1 = session4.createSQLQuery(str3).setParameter("Payscode", code)
				.setParameter("name", country.getName()).setParameter("devise", country.getDevise())
				.setParameter("greetings", country.getGreetings()).executeUpdate();
		trans3.commit();
		session4.close();
		return query1;
	}

	@Override
	public List<Country> CountriesOfContinent(String codeContinent) {
		Session session = getSession();
		Transaction tran = session.beginTransaction();
		String str = "FROM Country where codeContinent=:codeContinent";
		List<Country> countries = session.createQuery(str).setParameter("codeContinent", codeContinent).list();

		tran.commit();
		session.close();
		return countries;
	}

}
