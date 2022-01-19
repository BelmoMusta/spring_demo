package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.IContinentService;
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

		if(country != null){
			ICountryService countryService = applicationContext.getBean(ICountryService.class, country);
			System.out.println("WELCOME : " + countryService.welcome());
			System.out.println("Devise is :" + countryService.devise());
			System.out.println("Continent is :" + countryService.continent());
		}
		else {
			System.out.println("Country not found!");
		}

	}

	@Override
	public void dealWithContinentByCode(String code) {
		Continent continent = continentDao.getByCode(code);

		if(continent != null){
			IContinentService continentService = applicationContext.getBean(IContinentService.class, continent);
			System.out.println("Continent: "+continent.getName()+ "\n Countries: ");
			for(Country c:continent.getCountries()){
				System.out.println(c.toString());
			}
		}
		else {
			System.out.println("Continent doesn't exist!");
		}
	}

	@Override
	public void deleteCountry(String code){
		Country country = countryDAO.getByCode(code);
		if(country != null) {
			countryDAO.deleteByCode(country);
		}
		else {
			System.out.println("Country not found");
		}
	}

	@Override
	public void updateCountry(String codeCountry, String info){
		Country country = countryDAO.getByCode(codeCountry);
		String name, devise, greeting, continentCode;
		Continent continent;
		if(country != null) {
			if(info.split(",")[0].length() > 0) {
				name = info.split(",")[0];
				country.setName(name);
			}
			if(info.split(",")[1].length() > 0) {
				devise = info.split(",")[1];
				country.setDevise(devise);
			}
			if(info.split(",")[2].length() > 0) {
				greeting = info.split(",")[2];
				country.setGreetings(greeting);
			}
			if(info.split(",")[3].length() > 0) {
				continentCode = info.split(",")[3];
				continent = continentDao.getByCode(continentCode);
				country.setContinent(continent);
			}
			countryDAO.update(country);
		}
		else {
			System.out.println("Country doesn't exist!");
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
