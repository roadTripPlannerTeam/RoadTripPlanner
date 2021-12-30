package com.mycompany.roadtripplanner.dtos.itinerary;

import com.mycompany.roadtripplanner.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryUpdateDTO {
    private String id;
    private String title;
    private String experienceFeedback;
    private float nbStars;
//  private TodoList todoList;
//  private Budget budget
    List<Comment> comments;
//    private Map<>;
}
