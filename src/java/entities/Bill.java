/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import lombok.Data;

/**
 *
 * @author Admin
 */
@Entity
//@Data
public class Bill {
   @Id
   //@GeneratedValue(strategy=GenerationType.IDENTITY)        
   String id;
   @Column(name="Month_start_date")
   Date startDate;
   @Column(name="Month_end_date")
   Date endDate;
   @Column(name="Bill_Sub_Date")
   Date submitedDate;
   @Column(name="House_id")
   int HouseId;
   @Column(name="Bill_Amount")
   float amount;
   @Column(name="states")
   String states;
   
   @ManyToOne
   Tenants tenant;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getSubmitedDate() {
        return submitedDate;
    }

    public void setSubmitedDate(Date submitedDate) {
        this.submitedDate = submitedDate;
    }

    public int getHouseId() {
        return HouseId;
    }

    public void setHouseId(int HouseId) {
        this.HouseId = HouseId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public Tenants getTenant() {
        return tenant;
    }

    public void setTenant(Tenants tenant) {
        this.tenant = tenant;
    }
   
   
   
}
