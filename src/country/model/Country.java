package country.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "Country")
public class Country implements Serializable  {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;

	@Column(name ="name")
	private String name;

	@Column(name ="code")
	private String code;

	@Column(name ="devise")
	private String devise;

	@Column(name ="greetings")
	private String greetings;



	@ManyToOne
	private Continent continent;
	
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


	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}
}
