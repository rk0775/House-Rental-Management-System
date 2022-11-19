/*
* Tenants table
*/
package entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
//import lombok.Data;

//@Data
@Entity
public class Tenants {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Tenants_id",length=30)
    private int id;
    
    @Column(name="Tenants_Name",length=30)
    private String name;
    
    @Column(name="User_id",length=30)
    private int tid;
    
    @Column(name="Tenants_states",length=30)
    private String states;
    
    @Column(name="Tenants_BOD",length=10)
    private Date birthDate;
    
    @Column(name="Tenants_reason",length=30)
    private String reason;
    
    @Column(name="Tenants_type",length=30)
    private String type;
    
    @Column(name="Tenancy_Month",length=30)
    private int tenancyTime;
    
    @Column(name="Tenants_gender",length=7)
    private String gender;
    
    @Column(name="Tenant_Start_date")
    private Date startdate;
    
    @Column(name="Tenant_End_date")
    private Date endDate;
    
    @Column(name="Check_By_Admin")
    private boolean checkByAdmin;
    
    
    
    @OneToOne
    @JoinColumn(name="House_id")
    House house;
    
      
    @OneToMany (mappedBy="tenant",cascade=CascadeType.ALL)
    List<Bill> bills;
    
    public Tenants(){    
    }
    public void setIsCheckByAdmin(boolean v){
        this.checkByAdmin=v;
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTenancyTime() {
        return tenancyTime;
    }

    public void setTenancyTime(int tenancyTime) {
        this.tenancyTime = tenancyTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isCheckByAdmin() {
        return checkByAdmin;
    }

    public void setCheckByAdmin(boolean checkByAdmin) {
        this.checkByAdmin = checkByAdmin;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
    
    
}
