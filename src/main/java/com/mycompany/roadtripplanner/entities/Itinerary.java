package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    @Id
    private String id;
    private String title;
    private String experienceFeedback;
    private float nbStars;

    @DBRef
    private TodoList todoList;
    @DBRef
    private Budget budget;
    @DBRef
    private List<Comment> comments;
    @DBRef
    private  User user;

    private Date date ;
    @DBRef
    private Map<Date, Stage> stages;
}
