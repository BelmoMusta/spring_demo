package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;

@Transactional
public class Test {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/*.xml");
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
    CountryDAO Cdao = applicationContext.getBean(CountryDAO.class);
    
    @org.junit.Test
    public void checIfCountryExistsTest() {
    	assertTrue(serviceWorker.checkIfCountryExists("fr"));
    	assertFalse(serviceWorker.checkIfCountryExists("re")); //re n'existe pas
    }
    
    @org.junit.Test
    public void addCountryTest() {
    	serviceWorker.addCountry("Maroc,mr,Dirham,Salam,afr");
    	assertTrue(serviceWorker.checkIfCountryExists("mr"));
    	
    	List<String> l = serviceWorker.countryElements("mr");
    	assertEquals("Maroc", l.get(0));
    	assertEquals("mr", l.get(1));
    	assertEquals("Dirham", l.get(2));
    	assertEquals("Salam", l.get(3));
    	assertEquals("afr", l.get(4));
    }
    
    @org.junit.Test
    public void deleteCountryTest() {
    	serviceWorker.addCountry("Korea,kr,Won,Annyo!!,asi");
    	assertTrue(serviceWorker.checkIfCountryExists("kr"));
    	serviceWorker.deleteCountry("kr");
    	assertFalse(serviceWorker.checkIfCountryExists("kr"));
    }
    
    @org.junit.Test
    public void AffichageTest() {
    	Country c = new Country();
    	c.setName("Maroc");
    	c.setCode("mr");
    	c.setDevise("Dirham");
    	c.setGreetings("Salam");
    	c.setContinentCode("afr");
    	
    	serviceWorker.addCountry("Maroc,mr,Dirham,Salam,afr");
    	assertTrue(serviceWorker.checkIfCountryExists("mr")); 
    	
    	List<String> l = serviceWorker.countryElements("mr");
    	assertEquals("Maroc", l.get(0));
    	assertEquals("mr", l.get(1));
    	assertEquals("Dirham", l.get(2));
    	assertEquals("Salam", l.get(3));
    	assertEquals("afr", l.get(4));
    }
    
    @org.junit.Test
    public void updateCountryTest() {
    	serviceWorker.addCountry("Korea,kr,Won,Annyo!!,asi");
    	assertTrue(serviceWorker.checkIfCountryExists("kr"));
    	
    	String[] s = "Koreaaaaa,kr,Won,Annyo!!,asi".split(",", 5);
    	serviceWorker.updateCountry("kr", s);
    	
    	List<String> l = serviceWorker.countryElements("kr");
    	assertEquals("Koreaaaaa", l.get(0));
    	assertEquals("kr", l.get(1));
    	assertEquals("Won", l.get(2));
    	assertEquals("Annyo!!", l.get(3));
    	assertEquals("asi", l.get(4));
    }
}