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
	public Country getByCode(String countryCode) {
		/**/Country country = null;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country where code = ?;");
			preparedStatement.setString(1, countryCode);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				country = new Country();
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String code = resultSet.getString(3);
				String devise = resultSet.getString(4);
				String greetings = resultSet.getString(5);
				
				country.setId(id);
				country.setName(name);
				country.setCode(code);
				country.setDevise(devise);
				country.setGreetings(greetings);
				
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return country;
		
	}
	
	@Override
	public int insert(Country country,String nameOfContinet) {
		 int rowsAffected=0;
		country.setContinent(getContinentByName(nameOfContinet));
        if(country.getContinent()==null)
        	System.err.println("** Aucun continent avec le nom indiqué, seulement la première lettre en majiscule 'Afrique' **\n");
        else {
            if(getByCode(country.getCode())==null) {
        	Session session=getSessionFactory().openSession();
        	Transaction transaction = session.beginTransaction();
            Query query=session.createSQLQuery("INSERT INTO country(name, code, devise, greetings,continent_id) VALUES(:name, :code, :devise,:greeting,:continent);");
            query.setParameter("name",country.getName())
            .setParameter("code",country.getCode()).setParameter("devise",country.getDevise())
            .setParameter("greeting",country.getGreetings())
            .setParameter("continent",country.getContinent());
            
             rowsAffected = query.executeUpdate();
        	transaction.commit();
        	if (rowsAffected > 0) {
        	    System.out.println("** Merci, Un nouveau Pays est crée **");
        	}
            else
        		System.err.println("** Insertion n'a pas réussie **");
            }
            else 
            	System.err.println("** Le code exist déjà **");
       
	}return rowsAffected;
	}
	
	@Override
	public Continent getContinentByName(String name) {
		
		String hql="from Continent C where C.name =:name";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("name", name);
		return (Continent) query.uniqueResult();
	}
}
