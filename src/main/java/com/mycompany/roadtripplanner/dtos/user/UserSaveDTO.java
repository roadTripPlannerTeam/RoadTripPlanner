package com.mycompany.roadtripplanner.dtos.user;

import com.mycompany.roadtripplanner.entities.Adress;
import com.mycompany.roadtripplanner.entities.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthday;
    private Adress adress;
    private String profilPicture;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public UserSaveDTO(String firstName, String lastName, String email, String password, Date birthday, Adress adress, String profilPicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.profilPicture = profilPicture;


    }
}
