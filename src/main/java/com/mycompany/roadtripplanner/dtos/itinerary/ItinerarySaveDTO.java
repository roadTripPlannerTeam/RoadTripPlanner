package com.mycompany.roadtripplanner.dtos.itinerary;

import com.mycompany.roadtripplanner.dtos.budget.BudgetRelationDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListRelationDTO;
import com.mycompany.roadtripplanner.dtos.user.UserRelationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ItinerarySaveDTO {
    private String title;
    private String experienceFeedback;
    private float nbStars;
    private TodoListRelationDTO todoList;
    private BudgetRelationDTO budget;
    private UserRelationDTO user;
}
