package country.dao;

import country.model.Country;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
//	private DataSource dataSource;
	
	@Override
	public Country getByCode(String countryCode) {
		Country country = null;
		try {
//			Connection connection = dataSource.getConnection();			
//			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country where code = ?;");
//			preparedStatement.setString(1, countryCode);
//			ResultSet resultSet = preparedStatement.executeQuery();
			Session session = sessionFactory.openSession();
			Query<Country> query = session.createQuery("from country where code = :code");
			query.setParameter("code", countryCode);
			country =(Country)query.uniqueResult();
			session.close();			
//			if (resultSet.next()) {
//				country = new Country();
//				Integer id = resultSet.getInt(1);
//				String name = resultSet.getString(2);
//				String code = resultSet.getString(3);
//				String devise = resultSet.getString(4);
//				String greetings = resultSet.getString(5);
//				String codeContinent = resultSet.getString(6);
//				
//				country.setId(id);
//				country.setName(name);
//				country.setCode(code);
//				country.setDevise(devise);
//				country.setGreetings(greetings);
//				country.setCodeContinent(codeContinent);
//			}
		} catch (HibernateException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return country;
	}

	@Override
	public Country saveCountry(Country c) {
		Session session = sessionFactory.openSession();
		session.save(c);		
		session.close();
		Country country = this.getByCode(c.getCode());
		return country;
//		try {
//			Connection connection = dataSource.getConnection();
//			PreparedStatement pS = connection.prepareStatement("INSERT INTO country(name, code, devise, greetings, code_continent) VALUES(?,?,?,?,?);");
//			pS.setString(1, c.getName());
//			pS.setString(2, c.getCode());
//			pS.setString(3, c.getDevise());
//			pS.setString(4, c.getGreetings());
//			pS.setString(5, c.getCodeContinent());
//			pS.executeUpdate();
//			pS.close();
//			PreparedStatement pS2 = connection.prepareStatement("SELECT MAX(id) AS MAX_ID FROM country");
//			ResultSet rs = pS2.executeQuery();
//			if(rs.next()) {
//				c.setId(rs.getInt("MAX_ID"));
//			}
//			pS2.close();
//		} catch( SQLException e) {
//			e.printStackTrace();
//		}		
	}

	@Override
	public void deleteCountry(String deleteCode) {
		try {
			Session session = sessionFactory.openSession();		
			session.beginTransaction();
			session.delete(this.getByCode(deleteCode));
			session.getTransaction().commit();
			session.close();
			
		} catch (HibernateException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
//		try {
//			
//		Connection connection = dataSource.getConnection();
//		PreparedStatement pS = connection.prepareStatement("DELETE FROM country WHERE code=?");
//		pS.setString(1, code);
//		pS.executeUpdate();
//		pS.close();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public Country updateCountry(Country c, String code) {
		try {
			Session session = sessionFactory.openSession();	
			session.beginTransaction();
			c.setId(this.getByCode(code).getId());
			session.update(c);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
//		try {
//			Connection connection = dataSource.getConnection();
//			PreparedStatement pS = connection.prepareStatement("UPDATE country SET name=?, code=?, devise=?, greetings=?, code_continent=? WHERE code=?");
//			pS.setString(1, c.getName());
//			pS.setString(2, c.getCode());
//			pS.setString(3, c.getDevise());
//			pS.setString(4, c.getGreetings());
//			pS.setString(5, c.getCodeContinent());
//			pS.setString(6, code);
//			pS.executeUpdate();
//			pS.close();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
		return c;
	}

	@Override
	public List<Country> continentCountries(String code) {
		List<Country> l = new ArrayList<>();		
		try {
			Session session = sessionFactory.openSession();	
			session.beginTransaction();
//			c.setId(this.getByCode(code).getId());
			Query<Country> query = session.createQuery("from country WHERE code_continent = :code");
			query.setParameter("code", code);
			l =(List<Country>)query.list();			
			session.getTransaction().commit();
			session.close();
			
		} catch (HibernateException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
//		try {			
//			Connection connection = dataSource.getConnection();
//			PreparedStatement pS = connection.prepareStatement("SELECT * FROM country WHERE code_continent LIKE ?");
//			pS.setString(1, code);
//			ResultSet rs = pS.executeQuery();
//			while(rs.next()) {
//				cl = new Country();
//				cl.setId(rs.getInt(1));
//				cl.setName(rs.getString(2));
//				cl.setCode(rs.getString(3));
//				cl.setDevise(rs.getString(4));
//				cl.setGreetings(rs.getString(5));
//				cl.setCodeContinent(rs.getString(6));
//				l.add(cl);
//			}
//			pS.close();
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}
		return l;
	}
	
}
