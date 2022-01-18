package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO DAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void dealWithCountryByCode(String language) {
		Country pays = DAO.getByCode(language);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
		
		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
	}
	public void saveCountry(Country country)
	{
		DAO.saveCountry(country);
	}

	public List<Country> findCountrys()
	{
		return DAO.findCountrys();
	}

	public void deleteCountryByCode(String code)
	{
		DAO.deleteCountryByCode(code);
	}

	public Country getByCode(String code) {
		return DAO.getByCode(code);
		
	}
	public void updateCountry(Country code)
	{
		DAO.updateCountry(code);
	}
	


	
}
