package country.dao;
import country.model.Country;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import java.util.Iterator;
import java.util.List;

@Repository("countryDAO")
public class CountryDAOImpl extends AbsCountryDAO implements CountryDAO {
	
	@Override
	public void saveCountry(Country country) 
	{
		persist(country);

	}

	@Override
	public void listCountry() 
	{
		Session session = getSession();
	    Transaction transaction = session.beginTransaction();
		String str="FROM Country";
		List<Country> countries = session.createQuery(str).list();
		for (Iterator it =countries.iterator();
			it.hasNext();){
			Country country = (Country) it.next();
			System.out.print(" Name: " + country.getName());
			System.out.print(" ,currency: " + country.getDevise());
			System.out.print(" ,Greetings: " + country.getGreetings());
			System.out.println(",Code: " + country.getCode());
	}
		}
	@Override
	public void findByCode(String code) {
	
		Session session2 = getSession();
		Transaction transaction = session2.beginTransaction();
		String str1 = "FROM Country where code=:countrCode";
		List <Country> countries1 = session2.createQuery(str1).setParameter("countrCode", code).list();
		for (Iterator it =countries1.iterator(); 
		it.hasNext();){
        Country country = (Country) it.next();
		System.out.print(" Name: " + country.getName());
		System.out.print(" ,currency: " + country.getDevise());
		System.out.print(" ,Greetings: " + country.getGreetings());
		System.out.println(",Code: " + country.getCode());
		}
		transaction.commit();
		session2.close();}
	@Override
	public void deleteByCode(String code)
	{
		Session session3 = getSession();
		Transaction transaction2 = session3.beginTransaction();
		String str2 = "delete from Country where code = :codeID";
		int req = session3.createSQLQuery(str2).setParameter("codeID", code).executeUpdate();
		transaction2.commit();
		session3.close();
	}
 
	@Override
	public void updateByCode(String code, Country contry) {
		
		Session session4 = getSession();
		Transaction transaction3 = session4.beginTransaction();
		String str3 ="UPDATE Country SET name = :name ,devise =:devise ,greetings=:greetings  WHERE code = :codeID";
		int req2 = session4.createSQLQuery(str3).setParameter("codeID", code).setParameter("name", contry.getName()).setParameter("devise", contry.getDevise()).setParameter("greetings", contry.getGreetings()).executeUpdate();
		transaction3.commit();
		session4.close();
	}

	}
