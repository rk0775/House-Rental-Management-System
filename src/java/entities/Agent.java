/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import lombok.Data;

/**
 *
 * @author Admin
 */
//@Data
@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Agent_id")
    int id;
    @Column(name="Agent_name",length=50)
    String name;
    @Column(name="Agent_phone",length=12)
    String phone;
    @Column(name="Agent_mobile",length=12)
    String mobile;
    @Column(name="Agent_Email", length=50)
    String email;
    @Column(name="Agent_discription", length=500)
    String discription;
    @Column(name="Agent_address",length=500)
    String address;
    @Column(name="Agent_pic",length=30)
    String pic;
    @Column(name="Agent_states",length=10)
    String states;
    
    public Agent(){};
    
    public Agent(String name, String phone, String mobile, String email, String discription, String address, String pic,String states) {
        this.name = name;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.discription = discription;
        this.address = address;
        this.pic = pic;
        this.states=states;
    }
   
    public String getStates() {
    return states;
    }
    public String getAddress() {
    return address;
    }
    
    public String getDiscription() {
    return discription;
    }
    
    public String getEmail() {
    return email;
    }
    
    public int getId() {
    return id;
    }
    
    public String getMobile() {
    return mobile;
    }
    
    public String getName() {
    return name;
    }
    
    public String getPhone() {
    return phone;
    }
    
    public String getPic() {
    return pic;
    }
    
    public void setStates(String states){
    this.states=states;
    }
    
    public void setAddress(String address) {
    this.address = address;
    }
    
    public void setDiscription(String discription) {
    this.discription = discription;
    }
    
    public void setEmail(String email) {
    this.email = email;
    }
    
    public void setId(int id) {
    this.id = id;
    }
    
    public void setMobile(String mobile) {
    this.mobile = mobile;
    }
    
    public void setName(String name) {
    this.name = name;
    }
    
    public void setPhone(String phone) {
    this.phone = phone;
    }
    
    public void setPic(String pic) {
    this.pic = pic;
    } 
}
