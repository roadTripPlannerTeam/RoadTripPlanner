package com.mycompany.roadtripplanner.dtos.comment;

import com.mycompany.roadtripplanner.configurations.UserRelationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDTO {
    private String id;
    private String title;
    private String description;
    private Date date;
    private UserRelationDTO author;
}
