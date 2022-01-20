package country.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import country.model.Country;

public abstract class Dao
{
	@Autowired
	public SessionFactory sessionFactory;
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	
}