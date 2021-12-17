package com.mycompany.roadtripplanner.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class User {
    //Attribut
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String Password;
    private Date bithday;
    private String Adress;

    //CVonstructeur
    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Date bithday, String adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Password = password;
        this.bithday = bithday;
        Adress = adress;
    }

    //Getters setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Date getBithday() {
        return bithday;
    }

    public void setBithday(Date bithday) {
        this.bithday = bithday;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }
}
