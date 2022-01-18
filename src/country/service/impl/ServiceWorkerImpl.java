package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private ContinentDAO continentDao;
	
	@Override
	public void dealWithCountryByCode(String code) {
		Country country = countryDAO.getByCode(code);
		ICountryService countryService = applicationContext.getBean(ICountryService.class, country);

		if(country != null){
			System.out.println("WELCOME : " + countryService.welcome());
			System.out.println("Devise is :" + countryService.devise());
			System.out.println("Continent is :" + countryService.continent());
		}
		else {
			System.out.println("Country not found!");
		}

	}

	@Override
	public void saveCountry(String input) {
		Pattern pattern = Pattern.compile("(([A-Za-z]+),){4}(([A-Za-z]+)){1}");
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()){
			String code = input.split(",")[0];
			String name = input.split(",")[1];
			String devise = input.split(",")[2];
			String greeting = input.split(",")[3];
			String continentCode = input.split(",")[4];

			if(continentDao.continentExists(continentCode)){
				Continent continent = continentDao.getByCode(continentCode);
				Country country = new Country(name,code,devise,greeting,continent);
				try {
					countryDAO.save(country);
				}catch (Exception e){
					System.out.println("Country already exists!");
				}
			}else{
				System.out.println("Invalid continent code");
			}
		} else{
			System.out.println("Invalid Format");
		}

	}

}
