package country.TestPackage;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Configuration.Config;
import country.model.Country;
import country.service.IServiceWorker;


public class TestAspectFct {
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
	IServiceWorker sw = applicationContext.getBean(IServiceWorker.class);
	
	@Before
    public void setup() throws SQLException {
        org.h2.tools.Server.createTcpServer().start();
    }
    
	@Test
    public void testAjout() {

        sw.addCountry("al,Algerie,dinare,salam");
        Country c = sw.getCountryByCode("al");
        assertEquals("al",c.getCode());
        assertEquals("Algerie",c.getName());
        assertEquals("dinare",c.getDevise());
        assertEquals("salam",c.getGreetings());
        
    }
	
	@Test
    public void testSupression() {
        sw.deletByCode("fr");
        Country c1 = sw.getCountryByCode("fr");

        assertTrue(c1.equals(null));
    }
	
	@Test
    public void testModification() {
         Country c;
         sw.updateByCode("es", "es,maroc,DH,Salam");
         c = sw.getCountryByCode("es");
        assertEquals("es",c.getCode());
        assertEquals("maroc",c.getName());
        assertEquals("DH",c.getDevise());
        assertEquals("Salam",c.getGreetings());
        
    }

}
