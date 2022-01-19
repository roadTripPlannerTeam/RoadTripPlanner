package com.mycompany.roadtripplanner.payload.request;

import com.mycompany.roadtripplanner.entities.Adress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> roles;
    private String password;
    private Date birthday;
    private Adress Adress;
    private String profilPicture;

}
