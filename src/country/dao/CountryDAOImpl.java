package country.dao;

import country.model.Country;
import continent.model.Continent;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository("countryDao")
public class CountryDAOImpl extends Dao implements CountryDAO {

	@SuppressWarnings("unused")
	private Transaction trans;
	@Override
	public boolean saveCountry(Country country) {
		// TODO Auto-generated method stub
		Session session=getSession();
		   Transaction trans=session.beginTransaction();
		   try {
			   session.save(country);
			   trans.commit();
			   session.close();
			   return true;
		   }catch(Exception e){
			   System.out.print(e.getMessage());

		   }
		   return false;
	}

	@Override
	public Country findByCode(String code) {
		// TODO Auto-generated method stub
		Session session=getSession();
		   trans = session.beginTransaction();
		   Country countries=new Country();
		  try {
			   countries = (Country) session.createQuery("FROM Country where code=:countrycode").setParameter("countrycode", code).uniqueResult();
		  }
		  catch(Exception e)
		  {
			  System.out.print(e.getMessage());
		  }
		   trans.commit();
		   session.close();
		   return countries;
    }
	
	@Override
	public List<Country> listCountry() {
		// TODO Auto-generated method stub
		Session session=getSession();
		   trans = session.beginTransaction();
		  @SuppressWarnings("unchecked")
		   List<Country> countries =  session.createQuery("FROM Country").list();
		   trans.commit();
		   session.close();
		   return countries;
	}
	@Override
	public boolean deleteByCode(String code)
	{
		Session session = getSession();
		trans = session.beginTransaction();
		int query = session.createSQLQuery("delete from Country where code = :code").setParameter("code", code).executeUpdate();
		trans.commit();
		session.close();
		if(query>0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean updateByCode(String code, Country c) {
		// TODO Auto-generated method stub
		Session session = getSession();
		trans = session.beginTransaction();
		int query = session.createSQLQuery(
				"UPDATE Country SET name = :name ,devise =:devise ,greetings=:greetings  WHERE code = :code").setParameter("code", code).setParameter("name", c.getName()).setParameter("devise", c.getDevise()).setParameter("greetings", c.getGreetings()).executeUpdate();
		trans.commit();
		session.close();
		if(query>0)
			return true;
		else
			return false;
	}
	
	@Override
	public List<Country> getCountriesofContinent(String continentcode) {
		// TODO Auto-generated method stub
		Session session = getSession();
		trans = session.beginTransaction();
		Continent c= (Continent) session.createQuery("from Continent where code= :code").setParameter("code", continentcode).uniqueResult();
	    List<Country>contries=c.getCountry();
	   trans.commit();
	   session.close();
		return contries;
	}
	
}