package app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="country")
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
	@ManyToOne(cascade = CascadeType.REFRESH , fetch = FetchType.EAGER)
	@JoinColumn(name = "continent_id")
	private Continent continent;
	
	@Override
	public String toString() {
		return String.format("- [%s] , %s , %s , %s ( continent = [%s] )", getCode(),getName(),getDevise(),getGreetings(),getContinent().getName() );
	}
}
