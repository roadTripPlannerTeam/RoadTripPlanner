package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //Attribut
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String Password;
    private Date bithday;
    private Adress Adress;


}
