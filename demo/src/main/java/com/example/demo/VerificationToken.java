package com.example.demo;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.Date;


@Entity
public class VerificationToken {

    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Employee user;

    private Date expiryDate;

    public VerificationToken(String token, long id, Employee user, Date expiryDate) {
        this.token = token;
        this.id = id;
        this.user = user;
        this.expiryDate = expiryDate;
    }
    public VerificationToken(){}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return user;
    }

    public void setEmployee(Employee user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
