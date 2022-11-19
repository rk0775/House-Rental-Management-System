
package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import lombok.Data;

@Entity
//@Data
public class HousePhotos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="HousePhotos_id")
    int id;
    @Column(name="HousePhotos_name",length=30)
    String name;
    
    @ManyToOne
    House house;

    public HousePhotos(){
        //default constroctor
    }
     public HousePhotos(int id, String name, House house) {
    this.id = id;
    this.name = name;
    this.house = house;
    }
    
    public House getHouse() {
    return house;
    }
    
    public void setName(String name) {
    this.name = name;
    }
    
    public void setId(int id) {
    this.id = id;
    }
    
    public void setHouse(House house) {
    this.house = house;
    }
    
    public String getName() {
    return name;
    }
    
    public int getId() {
    return id;
    }
    
}
