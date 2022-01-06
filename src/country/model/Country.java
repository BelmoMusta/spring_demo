package country.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.ManyToAny;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "COUNTRY")
public class Country {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String code;
	private String devise;
	private String greetings;
	
}
