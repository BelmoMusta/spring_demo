package country.model;

import java.io.Serializable;


public class Continent implements Serializable {

    private Integer id;
    private String name;
    private String code;

    public Continent(){ }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    }
