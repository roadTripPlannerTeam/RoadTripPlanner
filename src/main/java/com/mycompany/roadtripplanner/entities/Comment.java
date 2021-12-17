package com.mycompany.roadtripplanner.entities;

import com.fasterxml.jackson.databind.DatabindException;
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
public class Comment {
    @Id
    private String id;
    private String title;
    private String description;
    private String user;
    private Date date;
}
