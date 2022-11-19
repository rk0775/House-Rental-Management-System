package entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
//import lombok.Data;

@Entity
//@Data
public class House 
{
    @Id
   // @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="House_id")
    int id;
    @Column(name="House_name",length=100)
    String name;
    @Column(name="Rent_Type",length=50)
    String rentType;
    @Column(name="House_pries")
    int rent;
    @Column(name="House_bed",length=20)
    String bedNo;
    @Column(name="House_bath",length=20)
    String bathNo;
    @Column(name="House_garage",length=20)
    String garageNo;
    @Column(name="House_district",length=50)
    String district;
    @Column(name="House_discription",length=1000)
    String discription;
    @Column(name="House_addr",length=550)
    String address;
    @Column(name="House_Amenities",length=300)
    String aminities;
    @Column(name="House_type")
    String houseType;
    @Column(name="Date")
    String date;
    
    @ManyToOne
    HouseOwner houseOwner;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="Tenants_id")
    Tenants tenants;
    
    @OneToMany (mappedBy="house",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    List<HousePhotos> photos;
  
    
    public House() {
    }//defualt consructor

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getBathNo() {
        return bathNo;
    }

    public void setBathNo(String bathNo) {
        this.bathNo = bathNo;
    }

    public String getGarageNo() {
        return garageNo;
    }

    public void setGarageNo(String garageNo) {
        this.garageNo = garageNo;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAminities() {
        return aminities;
    }

    public void setAminities(String aminities) {
        this.aminities = aminities;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Tenants getTenants() {
        return tenants;
    }

    public void setTenants(Tenants tenants) {
        this.tenants = tenants;
    }

    public List<HousePhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<HousePhotos> photos) {
        this.photos = photos;
    }


}
