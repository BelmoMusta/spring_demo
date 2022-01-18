package country.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public  abstract class AbsCountryDAO {
	@Autowired
	private  SessionFactory sF;
	
	public Session getSession()
	{
		return sF.getCurrentSession();
	}
	public void persist(Object obj)
	{
		   Session s=getSession();
		   Transaction trans=s.beginTransaction();
		   s.save(obj);
		   trans.commit();
	}

	public void delete(Object obj)
	{
		getSession().delete(obj);
	}

}