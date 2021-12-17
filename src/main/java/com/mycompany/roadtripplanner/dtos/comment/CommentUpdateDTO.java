package com.mycompany.roadtripplanner.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDTO {
    private String id;
    private String title;
    private String description;
    private String user;
    private Date date;
}
