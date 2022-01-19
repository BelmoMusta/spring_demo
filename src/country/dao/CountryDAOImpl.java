package country.dao;

import country.model.Country;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import country.model.Continent;

@Repository("hibernate")
public class CountryDAOImpl implements CountryDAO {
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
	
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	
	
@Override
	
	public Country getByCode(String code) {
		String hql="from Country C where C.code =:code";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("code", code);
		Country country=(Country) query.uniqueResult();
        return country;
	}
	
	@Override
	public int insert(Country country,String nameOfContinet) {
		 int affectedRows=0;
		country.setContinent(getContinentByName(nameOfContinet));
        if(country.getContinent()==null)
        	System.out.println("** Aucun continent avec le nom indiqu�, seulement la premi�re lettre en majiscule 'Afrique' **\n");
        else {
            if(getByCode(country.getCode())==null) {
        	Session session=getSessionFactory().openSession();
        	Transaction transaction = session.beginTransaction();
            Query query=session.createSQLQuery("INSERT INTO country(name, code, devise, greetings,continent_id) VALUES(:name, :code, :devise,:greeting,:continent);");
            query.setParameter("name",country.getName())
            .setParameter("code",country.getCode()).setParameter("devise",country.getDevise())
            .setParameter("greeting",country.getGreetings())
            .setParameter("continent",country.getContinent());
            
             affectedRows = query.executeUpdate();
        	transaction.commit();
        	if (affectedRows > 0) {
        	    System.out.println("** Merci, Un nouveau Pays est cr�e **");
        	}
            else
        		System.out.println("** Insertion n'a pas r�ussie **");
            }
            else 
            	System.out.println("** Le code exist d�j� **");
       
	}return affectedRows;
	}
	
	@Override
	public Continent getContinentByName(String name) {
		
		String hql="from Continent C where C.name =:name";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("name", name);
		return (Continent) query.uniqueResult();
	}
}
