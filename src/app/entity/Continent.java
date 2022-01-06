package app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "continent")
public class Continent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "continent_id")
	private Integer id;
	@Column( unique = true, nullable = false, length = 40)
	private String code;
	@Column( unique = true, nullable = false, length = 40)
	private String name;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "continent")
	private List<Country> countries;
	@Override
	public String toString() {
		return "Continent [ code=" + code + ", name=" + name + "]";
	}
	
	
}
