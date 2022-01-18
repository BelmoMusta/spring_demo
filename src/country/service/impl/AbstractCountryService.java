package country.service.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import country.model.Country;
import country.service.ICountryService;


public abstract class AbstractCountryService implements ICountryService {
	@Autowired 
	private org.hibernate.SessionFactory SessionFactory;
	
	protected Session getSession() {
		return SessionFactory.getCurrentSession();
	}
	
	public void persist(Object obj) {
		getSession().persist(obj);
	}
	
	public void delete(Object obj) {
		getSession().delete(obj);
	}
	
	public String welcome() {
		return getCountry().getGreetings();
	}
	
	@Override
	public String devise() {
		return getCountry().getDevise();
	}
	
	public abstract Country getCountry();// c'est quoi ton pays ?
}
