package country.dao;

import country.model.Country;
import net.bytebuddy.asm.Advice.This;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	public Country getByCode(String countryCode) {
		// TODO Auto-generated method stub
		Country country = null;
		try {
			Transaction t =null;
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query<Country> query = session.createQuery("from country where code = :code");
			query.setParameter("code",countryCode);
			country = query.uniqueResult();			
			t =session.getTransaction();
			t.commit();
			session.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		/*try {
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
		}*/
		return country;
	}
	public void SaveCountry(Country country) {
		// TODO Auto-generated method stub
		
		/*try {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO country (name, code, devise, greetings) VALUES(?,?,?,?);");
		preparedStatement.setString(1, country.getName());
		preparedStatement.setString(2, country.getCode());
		preparedStatement.setString(3, country.getDevise());
		preparedStatement.setString(4, country.getGreetings());
		
		preparedStatement.executeUpdate();
		
	} catch (SQLException exception) {
		LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
	}*/
	Session session =sessionFactory.openSession();
	session.save(country);
		
	}
	public void DeleteCountry(String countryCode) {
		// TODO Auto-generated method stub
		/*	try {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM country WHERE code=?;");
		preparedStatement.setString(1, countryCode);
		
		preparedStatement.executeUpdate();
		
	} catch (SQLException exception) {
		LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
	}
	*/
		Transaction t = null;
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	session.delete(this.getByCode(countryCode));
	t= session.getTransaction();
	t.commit();
	session.close();
		
	}
	public void UpdateCountry(String lang, Country country) {
		// TODO Auto-generated method stub
		
		/*	try {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE country SET name=?, code=?, devise=?, greetings=? WHERE code=?");
		preparedStatement.setString(1, country.getName());
		preparedStatement.setString(2, country.getCode());
		preparedStatement.setString(3, country.getDevise());
		preparedStatement.setString(4, country.getGreetings());
		preparedStatement.setString(5, langue);
		
		preparedStatement.executeUpdate();
		
	} catch (SQLException exception) {
		LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
	}*/
		Transaction t =null;
	Session session = sessionFactory.openSession();
	country.setId(getByCode(lang).getId());
	session.beginTransaction();
	session.update(country);
	t= session.getTransaction();
	t.commit();
	session.close();
		
	}
	public List<Country> ListCountries(String codeContinent) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from country WHERE code_cont = :code");
		query.setParameter("code",codeContinent);
		List<Country> l = query.getResultList();
		
		return l;
	}
	

}
