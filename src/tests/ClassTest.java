package tests;

import config.Config;
import country.model.Country;
import country.service.IServiceWorker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.fest.assertions.api.Assertions.assertThat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;


public class ClassTest {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
    @Before
    public void setup() throws SQLException {
        org.h2.tools.Server.createTcpServer().start();
    }
    @Test
    public void addCountry() {
        serviceWorker.addCountry("mr,maroc,dh,salam,afr");
        Country result = serviceWorker.getCountry("mr");
        assertEquals("mr",result.getCode());
        assertEquals("maroc",result.getName());
        assertEquals("dh",result.getDevise());
        assertEquals("salam",result.getGreetings());
        assertEquals("afr",result.getContinent().getCode());
        assertEquals("Africa",result.getContinent().getName());
    }
    @Test
    public void getCountry() {
        Country result = serviceWorker.getCountry("fr");
        assertEquals("fr",result.getCode());
        assertEquals("France",result.getName());
        assertEquals("EURO",result.getDevise());
        assertEquals("Bonjour",result.getGreetings());
        assertEquals("eup",result.getContinent().getCode());
        assertEquals("Europe",result.getContinent().getName());
    }
    @Test
    public void deleteCountry() {
        Country country;
        serviceWorker.deleteCountry("fr");
        country = serviceWorker.getCountry("fr");
        assertThat(country).isEqualTo(null);
    }
    @Test
    public void UpdateCountry() {
        Country result;
        serviceWorker.updateCountry("fr", "fr,france,EURO,Bonjour!,ame");
        result = serviceWorker.getCountry("fr");
        assertEquals("fr",result.getCode());
        assertEquals("france",result.getName());
        assertEquals("EURO",result.getDevise());
        assertEquals("Bonjour!",result.getGreetings());
        assertEquals("ame",result.getContinent().getCode());
        assertEquals("America",result.getContinent().getName());
    }
}
