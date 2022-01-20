package country.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import country.model.Continent;
import country.model.Country;
@Repository("hibernate")
@Transactional
public class CountryDAOImpl implements CountryDAO{
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
	public Continent getByName(String name) {
		
		String sql="from Continent C where C.name =:name";
		Query query=getSessionFactory().openSession().createQuery(sql);
		query.setParameter("name", name);
		return (Continent) query.uniqueResult();
	}
	@Override
	public Continent getContinentByCode(String code) {
		
		String sql="from Continent C where C.code =:code";
		Query query=getSessionFactory().openSession().createQuery(sql);
		query.setParameter("code", code);
		return (Continent) query.uniqueResult();
	}
   @Override
	
	public Country getByCode(String code) {
		String sql="from Country C where C.code =:code";
		Query query=getSessionFactory().openSession().createQuery(sql);
		query.setParameter("code", code);
		Country country=(Country) query.uniqueResult();
        return country;
        }

    public List<Country> getALL(){
	   return (List<Country>) sessionFactory.getCurrentSession().createQuery("from Country").list();
    }

    @Override
    public boolean exists(String code) {
	    Session session = sessionFactory.getCurrentSession();
	    Query query = session.createQuery("from Country c where c.code = :code");
	    query.setParameter("code",code);
	    return query.list().size() > 0;
    }

   @Override
	public void deleteCountry(String codeCountry) {
		if(exists(codeCountry)){
			Country country = getByCode(codeCountry);
			Session session = sessionFactory.getCurrentSession();
			session.delete(country);
		}
	}
	@Override
	public void saveCountry(Country c) {
		sessionFactory.getCurrentSession().save(c);
		
	}
	@Override
	public Country update(Country country) {
	
	    if(country.getContinent()==null)
	    	System.err.println("there is no continent with this name");
	    else {
	    	
		Session session=getSessionFactory().openSession();
		Transaction txn = session.beginTransaction();
		String sql = "update Country c set c.name=:name,c.code=:code,c.devise=:devise,c.greetings=:greeting,c.continent=:continent where c.code=:codeUpdate";
		Query query = session.createQuery(sql);
		 query.setParameter("name",country.getName())
		      .setParameter("code",country.getCode())
		      .setParameter("devise",country.getDevise())
		      .setParameter("greeting",country.getGreetings())
		      .setParameter("continent",country.getContinent())
		      .setParameter("codeToUpdate",country.getCode());
		txn.commit();
		
	    }
		return country;
	    }
}
		


   
