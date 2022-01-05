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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import country.model.Continent;
import country.model.Country;
@Repository("hibernate")
public class CountryDAOImp implements CountryDAO{
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
	public int add(Country country,String nameOfContinet) {
		 int rowsAffected=0;
		country.setContinent(getByName(nameOfContinet));
        if(country.getContinent()==null)
        	System.err.println("there is no continent with this name");
        else {
            if(getByCode(country.getCode())==null) {
        	Session session=getSessionFactory().openSession();
        	Transaction txn = session.beginTransaction();
            Query query=session.createSQLQuery("INSERT INTO country(name, code, devise, greetings,continent_id) VALUES(:name, :code, :devise,:greeting,:continent);");
            query.setParameter("name",country.getName()).setParameter("code",country.getCode()).setParameter("devise",country.getDevise()).setParameter("greeting",country.getGreetings()).setParameter("continent",country.getContinent());
             rowsAffected = query.executeUpdate();
        	txn.commit();
        	if (rowsAffected > 0) {
        	    System.out.println("Inserted " + rowsAffected + " rows.");
        	}
            else
        		System.err.println("Insertion not successful");
            }
            else 
            	System.err.println("this code already exists");
       
	}return rowsAffected;
	}
	@Override
	public Continent getByName(String name) {
		
		String hql="from Continent C where C.name =:name";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("name", name);
		return (Continent) query.uniqueResult();
	}
	@Override
	public Continent getContinentByCode(String code) {
		
		String hql="from Continent C where C.code =:code";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("code", code);
		return (Continent) query.uniqueResult();
	}
@Override
	
	public Country getByCode(String code) {
		String hql="from Country C where C.code =:code";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("code", code);
		Country country=(Country) query.uniqueResult();
        return country;
	}
@Override

public int deleteByCode(String code) {
	int rowsAffected=0;
	Session session=getSessionFactory().openSession();
	Transaction txn = session.beginTransaction();
	String hql = "delete from Country where code = :code";
	 
	Query query = session.createQuery(hql);
	query.setParameter("code",code);
	 
	rowsAffected = query.executeUpdate();
	txn.commit();
	if (rowsAffected > 0) {
	    System.out.println("Deleted " + rowsAffected + " rows.");
	}
	else
		System.err.println("delete not successful");
	return rowsAffected;
}
@Override

public int updateByCode(Country country,String code,String nameOfContinet) {
	int rowsAffected=0;
	country.setContinent(getByName(nameOfContinet));
    if(country.getContinent()==null)
    	System.err.println("there is no continent with this name");
    else {
    	
	Session session=getSessionFactory().openSession();
	Transaction txn = session.beginTransaction();
	String hql = "update Country c set c.name=:name,c.code=:code,c.devise=:devise,c.greetings=:greeting,c.continent=:continent where c.code=:codeToUpdate";
	Query query = session.createQuery(hql);
	 query.setParameter("name",country.getName())
	      .setParameter("code",country.getCode())
	      .setParameter("devise",country.getDevise())
	      .setParameter("greeting",country.getGreetings())
	      .setParameter("continent",country.getContinent())
	      .setParameter("codeToUpdate", code);
	rowsAffected = query.executeUpdate();
	txn.commit();
	if (rowsAffected > 0) {
	    System.out.println("Updates " + rowsAffected + " rows.");
	}
	else
		System.err.println("update not successful");
    	}
    	
return rowsAffected;
}
@Override
public List<Country> getCountrieByCode(String code) {
	Continent continent=getContinentByCode(code);
	Session session=getSessionFactory().openSession();
	String hql="FROM Country C WHERE C.continent=:continent";
	Query query = session.createQuery(hql);
	 query.setParameter("continent",continent);
	return query.getResultList();
}
}
