package com.mycompany.roadtripplanner.entities;

import com.mycompany.roadtripplanner.entities.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //Attribut
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthday;
    private Adress Adress;
    private String profilPicture;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    @DBRef
    private List<Comment>comments;

    @DBRef
    private List<Itinerary> itineraries;



    public User(String firstName, String lastName, String email, String password, Date birthday, Adress adress, String profilPicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.Adress =adress;
        this.birthday = birthday;
        this.profilPicture = profilPicture;
    }


}
