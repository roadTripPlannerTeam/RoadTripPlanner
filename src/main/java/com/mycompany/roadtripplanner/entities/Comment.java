package com.mycompany.roadtripplanner.entities;

import com.mycompany.roadtripplanner.configurations.UserRelationDTO;
import com.mycompany.roadtripplanner.dtos.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private String id;
    private String title;
    private String description;
    private Date date;
    @DBRef
    private User user;
}
