package country.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Continent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTINENT_ID")
    private Set<Country> countries;

    public Continent(String name, String code){
        this.name = name;
        this.code = code;
    }

    public Continent(){ }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    @Override
    public String toString(){
        return "(" + "id = " + id + ", name = " + name + "', code = " + code + "'"  + ")";
    }
    }
