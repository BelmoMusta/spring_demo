package country.dao;
import country.model.Country;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


@Repository("countryDAO")
public  class CountryDAOImpl extends Dao implements CountryDAO {

	private Transaction trans;
	@Override
	public void addCountry(Country country) {
		// TODO Auto-generated method stub
		Session session=getSession();
		   Transaction transaction=session.beginTransaction();
		   session.save(country);
		   transaction.commit();
		   session.close();
	}
	@Override
	public void displayCountry() {
		// TODO Auto-generated method stub

		   Session session=getSession();
		   Transaction transaction = session.beginTransaction();
		   List<Country> listepayes = session.createQuery("FROM Country").list();
		   for (Iterator iterator =listepayes.iterator(); iterator.hasNext();){
			Country country = (Country) iterator.next();
			System.out.print("> Name: " + country.getName()+"\n");
			System.out.print(" Devise: " + country.getDevise()+"\n");
			System.out.print(" Greetings: " + country.getGreetings()+"\n");
			}
		   transaction.commit();
		   session.close();
	}
	@Override
	public void displayByCode(String code) {

		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		String q1 = "FROM Country where code=:Code";
		List <Country> listepayes = session.createQuery(q1).setParameter("Code", code).list();
		for (Iterator iterator =listepayes.iterator(); iterator.hasNext();){
        Country country = (Country) iterator.next();
		System.out.print("> Name: " + country.getName()+"\n");
		System.out.print("Devise: " + country.getDevise()+"\n");
		System.out.print("Greetings: " + country.getGreetings()+"\n");
		System.out.println("Code: " + country.getCode()+"\n");
		}
		transaction.commit();
		session.close();
		}
	@Override
	public void deletByCode(String code)
	{
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		String q2 = "delete from Country where code = :Code";
		int query = session.createSQLQuery(q2).setParameter("Code", code).executeUpdate();
		transaction.commit();
		session.close();
	}
	
	
	

	
	
}
