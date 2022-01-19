package country.dao;

import country.model.Continent;
import country.model.Country;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;



import java.util.Iterator;
import java.util.List;



@Repository("countryDAO")
public class CountryDAOImpl extends AbstractCountryDAO implements CountryDAO  {

	@Override
	public void enregistrerCountry(Country country) 
	{
		persist(country);
		
	}
	
	@Override
	public void listCountry() 
	{
		Session session1 = getSession();
	    Transaction tran1 = session1.beginTransaction();
		String str1="FROM Country";
		List<Country> countryy1= session1.createQuery(str1).list();
		for (Iterator iterator =countryy1.iterator(); 
			iterator.hasNext();){
			Country country = (Country) iterator.next();
			System.out.print(" Nom: " + country.getName());
			System.out.print(" ,Devise: " + country.getDevise());
			System.out.print(" ,Greetings: " + country.getGreetings());
			System.out.println(",Code: " + country.getCode());
	}
		tran1.commit();
		session1.close();
		}
	
	@Override
	public void findByCode(String code) {
	
		Session session2 = getSession();
		Transaction trans2 = session2.beginTransaction();
		String str2 = "FROM Country where code=:countrCode";
		List <Country> country2= session2.createQuery(str2).setParameter("countrCode", code).list();
		for (Iterator iterator =country2.iterator(); 
		iterator.hasNext();
				)
		{
        Country country = (Country) iterator.next();
		System.out.print(" Nom: " + country.getName());
		System.out.print(" ,Devise: " + country.getDevise());
		System.out.print(" ,Greetings: " + country.getGreetings());
		System.out.println(",Code: " + country.getCode());
		}
		trans2.commit();
		session2.close();}
	@Override
	public void supprimerByCode(String code)
	{
		Session session3 = getSession();
		Transaction trans3 = session3.beginTransaction();
		String str3 = "delete from Country where code = :codeID";
		int query3 = session3.createSQLQuery(str3).setParameter("codeID", code).executeUpdate();
		trans3.commit();
		session3.close();
	}
 
	@Override
	public void modificationByCode(String code, Country contry) {
		
		Session session4 = getSession();
		Transaction trans4 = session4.beginTransaction();
		String str4 ="UPDATE Country SET name = :name ,devise =:devise ,greetings=:greetings  WHERE code = :codeID";
		int query4 = session4.createSQLQuery(str4).setParameter("codeID", code).setParameter("name", contry.getName()).setParameter("devise", contry.getDevise()).setParameter("greetings", contry.getGreetings()).executeUpdate();
		trans4.commit();
		session4.close();
	}

	@Override
	public List<Country> findCountryByContinent(String codecontinT) {
		List <Country> country5=null;
		Session session5 = getSession();
		Transaction trans5 = session5.beginTransaction();
		String str5 = "FROM Continent where code=:Iddcontint";
	    Query<Continent> contint = session5.createQuery(str5).setParameter("Iddcontint", codecontinT);
        country5=contint.uniqueResult().getCountry();
		trans5.commit();
		session5.close();
		return country5;
	}


}
