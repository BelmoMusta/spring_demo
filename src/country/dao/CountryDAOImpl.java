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
		tran.commit();
		session.close();
		}
	
	@Override
	public void findByCode(String code) {
	
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		String str1 = "FROM Country where code=:countrCode";
		List <Country> countries1 = session.createQuery(str1).setParameter("countrCode", code).list();
		for (Iterator iterator =countries1.iterator(); iterator.hasNext();){
        Country country = (Country) iterator.next();
		System.out.print(" Nom: " + country.getName());
		System.out.print(" ,Devise: " + country.getDevise());
		System.out.print(" ,Greetings: " + country.getGreetings());
		System.out.println(",Code: " + country.getCode());
		}
		trans.commit();
		session.close();}
	@Override
	public void supprimerByCode(String code)
	{
		Session session = getSession();
		Transaction trans2 = session.beginTransaction();
		String str2 = "delete from Country where code = :codeID";
		int query = session.createSQLQuery(str2).setParameter("codeID", code).executeUpdate();
		trans2.commit();
		session.close();
	}


}
