package country.dao;
import country.service.impl.AbstractCountryService;
import country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

@Repository("countryDAO")
public class CountryDAOImpl extends AbsCountryDAO implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void saveCountry(Country country) 
	{
		persist(country);

	}

	@Override
	public void listCountry() 
	{
		Session session = getSession();
	    Transaction tran = session.beginTransaction();
		String str="FROM Country";
		List<Country> countries = session.createQuery(str).list();
		for (Iterator iterator =countries.iterator(); iterator.hasNext();){
			Country country = (Country) iterator.next();
			System.out.print(" Nom: " + country.getName());
			System.out.print(" ,Devise: " + country.getDevise());
			System.out.print(" ,Greetings: " + country.getGreetings());
			System.out.println(",Code: " + country.getCode());
	}
		}
	@Override
	public void findByCode(String code) {
	
		Session session2 = getSession();
		Transaction trans = session2.beginTransaction();
		String str1 = "FROM Country where code=:countrCode";
		List <Country> countries1 = session2.createQuery(str1).setParameter("countrCode", code).list();
		for (Iterator iterator =countries1.iterator(); iterator.hasNext();){
        Country country = (Country) iterator.next();
		System.out.print(" Nom: " + country.getName());
		System.out.print(" ,Devise: " + country.getDevise());
		System.out.print(" ,Greetings: " + country.getGreetings());
		System.out.println(",Code: " + country.getCode());
		}
		trans.commit();
		session2.close();}
	@Override
	public void deleteByCode(String code)
	{
		Session session3 = getSession();
		Transaction trans2 = session3.beginTransaction();
		String str2 = "delete from Country where code = :codeID";
		int query = session3.createSQLQuery(str2).setParameter("codeID", code).executeUpdate();
		trans2.commit();
		session3.close();
	}
 
	@Override
	public void updateByCode(String code, Country contry) {
		
		Session session4 = getSession();
		Transaction trans3 = session4.beginTransaction();
		String str3 ="UPDATE Country SET name = :name ,devise =:devise ,greetings=:greetings  WHERE code = :codeID";
		int query1 = session4.createSQLQuery(str3).setParameter("codeID", code).setParameter("name", contry.getName()).setParameter("devise", contry.getDevise()).setParameter("greetings", contry.getGreetings()).executeUpdate();
		trans3.commit();
		session4.close();
	}

	}
