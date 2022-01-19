package country.model;

import javax.persistence.*;

//import org.hibernate.annotations.*;

@Entity
@Table(name="country")
public class Country {
	/*@ManyToOne
	private Continent continent;
	*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="code",nullable=false)
	private String code;
	
	@Column(name="devise",nullable=false)
	private String devise;
	
	@Column(name="greetings",nullable=false)
	private String greetings;
	
	public Country(String code, String name, String devise,String greetings/*,Continent continent*/) {
		this.code = code;
		this.name = name;
		this.devise = devise;
		this.greetings = greetings;
		//this.continent=continent;
	}

	public Country() {
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDevise() {
		return devise;
	}
	
	public void setDevise(String devise) {
		this.devise = devise;
	}
	
	public String getGreetings() {
		return greetings;
	}
	
	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}
	
	/*public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}*/
}
