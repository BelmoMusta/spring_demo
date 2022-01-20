package country.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Continent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="CONTINENT_ID")
    private List<Country> countries;

    public Continent() {

    }
    public Continent(String name, String code) {

        this.name= name;
        this.code=code;
    }


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

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }


}
