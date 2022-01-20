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

	@Override
	public void deleteCountry(String code) {
		
		Country country=countryDao.getByCode(code);
		if(country!=null) {
		countryDao.deleteCountry(country);	
		System.out.println(country.getCode()+" is deleted! succefully");}
		else {
			System.out.println(" error code doesn't exist");
		}
	}

	@Override
	public void updateCountry(String newInformations) {
		try {
		String[] newElements = newInformations.split(",",5);
		String code=newElements[0];
		Country country=new Country();
		country.setCode(newElements[0]);
		country.setName(newElements[1]);
		country.setDevise(newElements[2]);
		country.setGreet(newElements[3]);
		country.setContinent(newElements[4]);
		Country newCountry=null;
		if(countryDao.getByCode(code)!=null) {
		newCountry = countryDao.updateCountry(country, code);
		}
		else {
			System.out.println("because the code doesn't exist the system create by default the country");
			newCountry = countryDao.saveCountry(country);
		}
		ICountryService countryService = applicationContext.getBean(ICountryService.class, newCountry);
		this.afficheCountry(newCountry.getCode());
        }
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadContinents(String continentCode) {
		List<Country> listsCountrys = countryDao.continentCountries(continentCode);
		for(Country country: listsCountrys) {
			this.afficheCountry(country.getCode());
		}
		
	}
	
}
