package country.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public  abstract class AbstractCountryDAO {
	@Autowired
	private  SessionFactory sessionFactory;
	

	public Session getSession()
	{
		
		return sessionFactory.getCurrentSession();
	}

	public void persist(Object entity)
	{

		   Session session=getSession();
		   Transaction trans=session.beginTransaction();
		   session.save(entity);
		   trans.commit();
	}

	public void delete(Object entity)
	{
		getSession().delete(entity);
	}
	

}
