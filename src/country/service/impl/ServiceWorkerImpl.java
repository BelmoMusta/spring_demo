package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;

	@Override
	public void InsertCountry(Country country, String nameOfContinet) {
		int rowsAffected = countryDAO.add(country, nameOfContinet);
		if (rowsAffected > 0)
			System.out.println("Inserted " + rowsAffected + " rows.");
		else if (rowsAffected == -1)
			System.err.println("there is no continent with this name");
		else if (rowsAffected == -2)
			System.err.println("this code is already exists");
		else
			System.err.println("Insertion not successful");

	}

	@Override
	public void selectCountry(String code) {
		Country country = countryDAO.getByCode(code);
		if (country == null)
			System.err.println("no country with this code ");
		else {
			System.out.println("Id :" + country.getId());
			System.out.println("Name :" + country.getName());
			System.out.println("Code :" + country.getCode());
			System.out.println("Devise :" + country.getDevise());
			System.out.println("Greeting :" + country.getGreetings());
			System.out.println("Continent :" + country.getContinent().getName());
		}
	}

	@Override
	public void deleteCountry(String code) {
		int rowsAffected = countryDAO.deleteByCode(code);
		if (rowsAffected == -2)
			System.err.println("no country with this code ");
		else if (rowsAffected > 0) {
			System.out.println("Deleted " + rowsAffected + " rows.");
		} else
			System.err.println("delete not successful");
	}

	@Override
	public void updateCountry(Country country, String code, String nameOfContinet) {
		int rowsAffected = countryDAO.updateByCode(country, code, nameOfContinet);
		if (rowsAffected > 0)
			System.out.println("Updates " + rowsAffected + " rows.");
		else if (rowsAffected == -1)
			System.err.println("there is no continent with this name");
		else if (rowsAffected == -2)
			System.err.println("this code is already exists");
		else
			System.err.println("update not successful");
	}

	@Override
	public boolean isExist(String code) {
		return countryDAO.getByCode(code) == null ? false : true;
	}

	@Override
	public void selectCountriesOfContinent(String code) {
		List<Country> countries = countryDAO.getCountrieByCode(code);
		if (countries == null)
			System.err.println("No continent with this code");
		else if (countries.size() == 0)
			System.err.println("No country in this continent");
		else {
			for (Country country : countries) {
				System.out.println("Id :" + country.getId());
				System.out.println("Name :" + country.getName());
				System.out.println("Code :" + country.getCode());
				System.out.println("Devise :" + country.getDevise());
				System.out.println("Greeting :" + country.getGreetings());
				System.out.println("---------------------------------");
			}
		}
	}
}
