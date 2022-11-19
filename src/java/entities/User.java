/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import HelperClasses.EntityHelper;
import java.util.ArrayList;
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

/**
 *
 * @author Admin
 */
@Entity
//@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="User_id")
    int id;
    @Column(name="User_name",length=100)
    String name;
    @Column(name="User_email",length=150)
    String email;
    @Column(name="User_phone",length=12)
    String phone;
    @Column(name="User_mobile",length=12)
    String mobile;
    @Column(name="User_password",length=20)
    String password;
    @Column(name="User_district",length=40)
    String district;
    @Column(name="User_tal",length=40)
    String tal;
    @Column(name="User_addr",length=500)
    String address;
    @Column(name="User_pic",length=50)
    String pic;
    @Column(name="User_type",length=50)
    String type;
    
    @OneToMany (mappedBy="user",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    List<Payment> payments;
    
    
    public User(){
        //default constructor
    }
    public User(String name, String email, String phone, String mobile, String password, String district, String tal, String address, String pic,String type) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.mobile = mobile;
    this.password = password;
    this.district = district;
    this.tal = tal;
    this.address = address;
    this.pic = pic;
    this.type=type;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTal() {
        return tal;
    }

    public void setTal(String tal) {
        this.tal = tal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
    
       
        

}
