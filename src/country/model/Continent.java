package country.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Continent {
	@Id
	private Integer id;
	private String code;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ContinentId")
	List<Country> countries;
}
