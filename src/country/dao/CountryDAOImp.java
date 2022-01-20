package country.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import country.model.Continent;
import country.model.Country;
@Repository("hibernate")
public class CountryDAOImp implements CountryDAO{
	
	@Autowired
    SessionFactory  sessionFactory;
	
	@Autowired
	DataSource dataSource;
	
	//getters & setters of sessionFactory
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    @Override
	
	public Country getByCode(String code) {
		
		Query query=getSessionFactory().openSession().createQuery("from Country C where C.code =:code");
		query.setParameter("code", code);
		Country country=(Country) query.uniqueResult();
        return country;
	}
	
	
	@Override
	public int AjouterCountry(Country country,String continent) {
		
		       
	        
		int Ajout=0;
		
	        	Session session=getSessionFactory().openSession();
	        	Transaction txn = session.beginTransaction();
	        	country.setContinent(getByName(continent));
	            Query query=session.createSQLQuery("INSERT INTO country(name, code, devise, greetings,continent_id) VALUES(:name, :code, :devise,:greeting,:continent);");
	            query.setParameter("name",country.getName()).setParameter("code",country.getCode()).setParameter("devise",country.getDevise()).setParameter("greeting",country.getGreetings()).setParameter("continent",country.getContinent());
	            Ajout = query.executeUpdate();
	        	txn.commit();
	        	if (Ajout > 0) {
	        	    System.out.println("Le Pays a été ajouté avec succès.");
	        	}
	            else
	        		System.err.println("Echec d'ajout !");
	            
	            
	       
		return Ajout;

	}
	
	

@Override

public int supprimerByCode(String code) {
	
	
	int supp=0;
	Session session=getSessionFactory().openSession();
	Transaction txn = session.beginTransaction();
	String hql = "delete from Country where code = :code";
	 
	Query query = session.createQuery(hql);
	query.setParameter("code",code);
	 
	supp = query.executeUpdate();
	txn.commit();
	
	if (supp > 0) {
	    System.out.println(" Suppression du pays avec succès. ");
	}
	else
		System.err.println("Echec de suppression !");
	
	return supp;
}
@Override

public int modifierByCode(Country country,String code,String continent) {
	
	int mod=0;
	Session session=getSessionFactory().openSession();
	Transaction txn = session.beginTransaction();
	String hql = "update Country c set c.name=:name,c.code=:code,c.devise=:devise,c.greetings=:greeting,c.continent=:continent where c.code=:codemodif";
	Query query = session.createQuery(hql);
	 query.setParameter("name",country.getName()).setParameter("code",country.getCode()).setParameter("devise",country.getDevise()).setParameter("greeting",country.getGreetings()).setParameter("continent",country.getContinent()) .setParameter("codemodif", code);
	 mod = query.executeUpdate();
	txn.commit();
	if (mod > 0) {
	    System.out.println("Modification du pays avec succès. ");
	}
	else
		System.err.println("Echec de modification !");
    	
    	
return mod;
}
@Override
public List<Country> getCountrieByCode(String code) {
	
	Query query = getSessionFactory().openSession().createQuery("FROM Country C WHERE C.continent=:continent");
	 query.setParameter("continent",getContinentByCode(code));
	return query.getResultList();
}

@Override
public Continent getByName(String name) {
	

	Query query=getSessionFactory().openSession().createQuery("from Continent C where C.name =:name");
	query.setParameter("name", name);
	return (Continent) query.uniqueResult();
}
@Override
public Continent getContinentByCode(String code) {
	
	
	Query query=getSessionFactory().openSession().createQuery("from Continent C where C.code =:code");
	query.setParameter("code", code);
	return (Continent) query.uniqueResult();
}
}
