package country.dao;

import country.model.Country;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
	    Transaction tran = session1.beginTransaction();
		String str="FROM Country";
		List<Country> countries = session1.createQuery(str).list();
		for (Iterator iterator =countries.iterator(); iterator.hasNext();){
			Country country = (Country) iterator.next();
			System.out.print(" Nom: " + country.getName());
			System.out.print(" ,Devise: " + country.getDevise());
			System.out.print(" ,Greetings: " + country.getGreetings());
			System.out.println(",Code: " + country.getCode());
	}
		tran.commit();
		session1.close();
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
	public void supprimerByCode(String code)
	{
		Session session3 = getSession();
		Transaction trans2 = session3.beginTransaction();
		String str2 = "delete from Country where code = :codeID";
		int query = session3.createSQLQuery(str2).setParameter("codeID", code).executeUpdate();
		trans2.commit();
		session3.close();
	}
 
	@Override
	public void modificationByCode(String code, Country contry) {
		
		Session session4 = getSession();
		Transaction trans3 = session4.beginTransaction();
		String str3 ="UPDATE Country SET name = :name ,devise =:devise ,greetings=:greetings  WHERE code = :codeID";
		int query1 = session4.createSQLQuery(str3).setParameter("codeID", code).setParameter("name", contry.getName()).setParameter("devise", contry.getDevise()).setParameter("greetings", contry.getGreetings()).executeUpdate();
		trans3.commit();
		session4.close();
	}


}
