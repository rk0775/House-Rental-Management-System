/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import lombok.Data;

/**
 *
 * @author Admin
 */
@Entity
//@Data
public class Payment {
    @Id
    @Column(name="receipt_Id")        
    String receiptId;
    @Column(name="House_ID")
    int HouseId;
    @Column(name="Payment_Type")
    String type;
    @Column(name="Amount")
    float amount;
    @Column(name="Date")
    Date date;
    
    @ManyToOne
    User user;

    public Payment(String receiptId, int HouseId, String type, float amount, Date date, User user) {
        this.receiptId = receiptId;
        this.HouseId = HouseId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }
    public Payment(){};

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public int getHouseId() {
        return HouseId;
    }

    public void setHouseId(int HouseId) {
        this.HouseId = HouseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
