package com.mycompany.roadtripplanner.dtos.user;

import com.mycompany.roadtripplanner.entities.Adress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date bithday;
    private Adress adress;
    private String profilPicture;

}
