package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

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
//  private TodoList todoList;
//  private Budget budget
    List<Comment> comments;

  //   Map<Date, Stage> toto;
}
