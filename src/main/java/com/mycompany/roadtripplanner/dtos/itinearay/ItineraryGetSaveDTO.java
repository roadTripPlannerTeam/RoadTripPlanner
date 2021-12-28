package com.mycompany.roadtripplanner.dtos.itinearay;

import com.mycompany.roadtripplanner.dtos.budget.BudgetDTO;
import com.mycompany.roadtripplanner.dtos.budget.BudgetRelationDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListRelationDTO;
import com.mycompany.roadtripplanner.dtos.user.UserRelationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryGetSaveDTO {
    private String id;
    private String title;
    private String experienceFeedback;
    private float nbStars;
    private TodoListRelationDTO todoList;
    private BudgetRelationDTO budget;
    private UserRelationDTO user;
}
