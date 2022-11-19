package entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import lombok.Data;

@Entity
//@Data
public class HouseOwner{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    
    @Column(name="Owner_id")
    int oid;
    @Column(name="Owner_name")
    String name;
    
    @OneToMany(mappedBy="houseOwner",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    List<House> house;

    public HouseOwner() {
    }
    
    public HouseOwner(int oid, String name, List<House> house) {
    this.oid = oid;
    this.name = name;
    this.house = house;
    }
    
    public List<House> getHouse() {
    return house;
    }
    
    public int getId() {
    return id;
    }
    
    public String getName() {
    return name;
    }
    
    public int getOid() {
    return oid;
    }
    
    public void setHouse(List<House> house) {
    this.house = house;
    }
    
    public void setId(int id) {
    this.id = id;
    }
    
    public void setName(String name) {
    this.name = name;
    }
    
    public void setOid(int oid) {
    this.oid = oid;
    }
    
}
