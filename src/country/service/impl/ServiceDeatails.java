package country.service.impl;

import country.domain.Country;
import country.repository.ICountryDao;
import country.service.ICountryService;
import country.service.IServiceDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeatails implements IServiceDetail {
	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void afficheCountry(String code) {
		Country pays = countryDao.getByCode(code);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
		
		System.out.println("COUNTRY : "+ pays.getName()+"\n WELCOME : " + countryService.welcome()+"\n Devise is :" + countryService.devise());
		
	}

	@Override
	public void createCountry(String informations) {
		String[] elements = informations.split(",",5);
		Country country=new Country();
		country.setCode(elements[0]);
		country.setName(elements[1]);
		country.setDevise(elements[2]);
		country.setGreet(elements[3]);
		country.setContinent(elements[4]);
		Country countrySaved=null;
		if(countryDao.getByCode(elements[0])==null) {
		countrySaved = countryDao.saveCountry(country);}
		else {
			System.out.println("these country exist deja  to modify click 4");
			countrySaved = countryDao.getByCode(elements[0]);
		}
		ICountryService countryService = applicationContext.getBean(ICountryService.class, countrySaved);
		this.afficheCountry(countrySaved.getCode());
	}

	
	
}
