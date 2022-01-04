package continent.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import country.model.Country;
import lombok.Data;

@Entity
@Data
public class Continent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column( unique = true, nullable = false, length = 40)
	private String code;
	@Column( unique = true, nullable = false, length = 40)
	private String name;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "continent")
	private Country country;
}
