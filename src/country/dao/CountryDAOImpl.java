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
		   Transaction trans=session.beginTransaction();
		   session.save(country);
		   trans.commit();
		   session.close();
	}
	@Override
	public void displayCountry() {
		// TODO Auto-generated method stub

			Session session=getSession();
		   trans = session.beginTransaction();
		   List<Country> listepayes = session.createQuery("FROM Country").list();
		   for (Iterator iterator =listepayes.iterator(); iterator.hasNext();){
			Country country = (Country) iterator.next();
			System.out.print("> Name: " + country.getName()+"\n");
			System.out.print(" Devise: " + country.getDevise()+"\n");
			System.out.print(" Greetings: " + country.getGreetings()+"\n");
			}
		   trans.commit();
		   session.close();
	}
	
	

	
	
}
