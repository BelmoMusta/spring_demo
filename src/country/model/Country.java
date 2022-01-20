package country.model;

import javax.persistence.*;

@Entity
@Table
public class Country {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String name;
	private String code;
	private String devise;
	private String greetings;

	@ManyToOne
	@JoinColumn(name="continent_id", nullable=true)
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

	@Override
	public String toString() {
		return "Pays {" +
				" Nom = '" + name + '\'' +
				", Code = '" + code + '\'' +
				", Devise = '" + devise + '\'' +
				", Salutation='" + greetings + '\'' +
				", Continent='" + continent.getName() + '\'' +
				'}';
	}
}
