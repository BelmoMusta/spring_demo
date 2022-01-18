package country.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Continent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String code;
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "continent",cascade = CascadeType.ALL)
	private Collection<Country> countries;


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

	public Collection<Country> getCountries() {
		return countries;
	}

	public void setCountries(Collection<Country> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return "Continent{" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", countries=" + countries +
				'}';
	}
}
