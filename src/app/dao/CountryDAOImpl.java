package app.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import app.entity.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Country getByCode(String countryCode) {
		String hql = "from Country C where C.code = ?1";
		Query<?> query = this.getSessionFactory().createQuery(hql);
		query.setParameter(1, countryCode);
		return (Country) query.uniqueResult();
	}

	@Override
	public Country addCountry(Country country) {
		hibernateTemplate.save(country);
		return country;
	}

	@Override
	public Country getCountry(String code) {

		return this.getByCode(code);
	}

	@Override
	public List<Country> getCountries() {
		
		return hibernateTemplate.loadAll(Country.class);
	}

	@Override
	public void deleteCountry(String code) {
		String hql = "DELETE FROM Country where code = ?1";
		Query<?> query = this.getSessionFactory().createQuery(hql);
		query.setParameter(1, code);
		query.executeUpdate();
	}

	@Override
	public Country updateCountry(Country country) {
		System.out.println(country.getId());
		hibernateTemplate.update(country);	
		return country;
	}

	@Override
	public List<Country> getCountriesByContinent(String continent) {
		String hql = "from Country C where C.continent.code = ?1";
		Query<?> query = this.getSessionFactory().createQuery(hql);
		query.setParameter(1, continent);
		return  query.getResultList().stream().map(el -> (Country)el).collect(Collectors.toList());
	}

	private final Session getSessionFactory() {
		return sessionFactory.getCurrentSession();
	}
}
