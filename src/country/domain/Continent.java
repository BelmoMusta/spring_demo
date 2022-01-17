package country.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="continent")
@Table
public class Continent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8057364861138416013L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String code;
	@Column	
	private String name;
	
	public Continent(String code, String name) {
		super();
		this.code=code;
		this.name=name;
	}
}
