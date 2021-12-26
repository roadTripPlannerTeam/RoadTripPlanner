package com.mycompany.roadtripplanner.dtos.user;

import com.mycompany.roadtripplanner.entities.Adress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class UserGetSaveDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date bithday;
    private Adress adress;
    private String profilPicture;
}
