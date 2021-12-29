package com.mycompany.roadtripplanner.dtos.commentResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseGetDTO {
    private String id;
    private String title;
    private String description;
    private Date date;
    private int like;
}
