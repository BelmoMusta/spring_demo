package country.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import continent.model.Continent;
import lombok.Data;

@Entity
@Data
public class Country {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column( unique = true, nullable = false, length = 40)
	private String name;
	@Column( unique = true, nullable = false, length = 20)
	private String code;
	@Column( unique = false, nullable = false, length = 20)
	private String devise;
	@Column( unique = false, nullable = false, length = 20)
	private String greetings;
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Continent continent;
}
