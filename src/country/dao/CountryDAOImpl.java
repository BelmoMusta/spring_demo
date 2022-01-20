package country.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import country.model.Continent;
import country.model.Country;
import country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository("hibernate")
@Transactional 
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
    SessionFactory  sessionFactory;
	@Autowired
	DataSource dataSource;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Country getByCode(String countryCode) {
		
		
			String hql="from Country C where C.code =:code";
			Query query=getSessionFactory().openSession().createQuery(hql);
			query.setParameter("code", countryCode);
			Country country=(Country) query.uniqueResult();
	        return country;
		
	}
	
	@Override
	public Continent getByName(String name) {
		
		String hql="from Continent C where C.name =:name";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("name", name);
		return (Continent) query.uniqueResult();
	}

	@Override
	public int ajouter(Country country,String _nameContinet) {
		 int rows=0;
		country.setContinent(getByName(_nameContinet));
        if(country.getContinent()==null)System.err.println("il n'y a pas de continent avec ce nom ");
        	
        else {
            if(getByCode(country.getCode())==null) {
        	Session session=getSessionFactory().openSession();
        	Transaction t = session.beginTransaction();
            Query query=session.createSQLQuery("INSERT INTO country(name, code, devise, greetings,continent_id) VALUES(:name, :code, :devise,:greeting,:continent);");
            query.setParameter("name",country.getName()).setParameter("code",country.getCode()).setParameter("devise",country.getDevise()).setParameter("greeting",country.getGreetings()).setParameter("continent",country.getContinent());
            rows = query.executeUpdate();
        	t.commit();
        	if (rows > 0)  System.out.println("ajouter " + rows + " ligne.");
        	
            else System.err.println("Insertion is DONE");
            }
            else System.err.println("this code already exists");
            	  
	}
        return rows;
	}
	@Override

	public int SuppByCode(String _inputCode) {
		if (getByCode(_inputCode) == null)
			return -2;
		Query query = getSession().createQuery("delete from Country where code = :code");
		query.setParameter("code", _inputCode);
		return query.executeUpdate();
	}
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public int updateByCode(Country country, String _inputCode, String _nameContinet) {
		country.setContinent(getByName(_nameContinet));
		if (country.getContinent() == null)
			return -1;
		else if (getByCode(country.getCode()) != null)
			return -2;
		else {
			Query query = getSession().createQuery(
					"update Country c set c.name=:name,c.code=:code,c.devise=:devise,c.greetings=:greeting,c.continent=:continent where c.code=:codeToUpdate");
			query.setParameter("name", country.getName()).setParameter("code", country.getCode())
					.setParameter("devise", country.getDevise()).setParameter("greeting", country.getGreetings())
					.setParameter("continent", country.getContinent()).setParameter("codeToUpdate", _inputCode);
			return query.executeUpdate();
		}
	}
	@Override
	public List<Country> getCountrieByCode(String code) {
		Continent continent = getContinentByCode(code);
		if (continent == null)
			return null;
		Query query = getSession().createQuery("FROM Country C WHERE C.continent=:continent");
		query.setParameter("continent", continent);
		return query.getResultList();
	}
	@Override
	public Continent getContinentByCode(String code) {
		String hql = "from Continent C where C.code =:code";
		Query query = getSession().createQuery(hql);
		query.setParameter("code", code);
		return (Continent) query.uniqueResult();
	}

}
