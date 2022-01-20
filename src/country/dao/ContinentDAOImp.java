package country.dao;

import org.hibernate.query.Query;

import country.model.Continent;

public class ContinentDAOImp {

	/*@Override
	public Continent getByName(String name) {
		
		String hql="from Continent C where C.name =:name";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("name", name);
		return (Continent) query.uniqueResult();
	}
	@Override
	public Continent getContinentByCode(String code) {
		
		String hql="from Continent C where C.code =:code";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("code", code);
		return (Continent) query.uniqueResult();
	}
	 */
}
