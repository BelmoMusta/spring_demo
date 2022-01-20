package country.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Country implements Serializable{

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String code;
	private String devise;
	private String greetings;
	@ManyToOne
	private Continent continent;

	public Country(String name, String code, String devise, String greetings, Continent continent){
		this.name = name;
		this.code = code;
		this.devise = devise;
		this.greetings = greetings;
		this.continent = continent;
	}

	public Country(){ }
	
	public Integer getId() { return id; }
	
	public void setId(Integer id) { this.id = id; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public String getCode() { return code; }
	
	public void setCode(String code) { this.code = code; }
	
	public String getDevise() { return devise; }
	
	public void setDevise(String devise) { this.devise = devise; }
	
	public String getGreetings() { return greetings; }
	
	public void setGreetings(String greetings) { this.greetings = greetings; }

	public void setContinent(Continent continent){ this.continent = continent; }

	public Continent getContinent(){ return continent; }

	@Override
	public String toString(){
		return "(" + "id=  " + id + ", name = " + name + ", code = " + code + ", devise = " + devise + ", greetings = " + greetings + ", continent = " + continent.getName() + ")";
	}
}
