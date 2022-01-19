package country.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Continent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="CONTINENT_ID")
    private Set<Country> countries;

    public Continent(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Continent() {
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

