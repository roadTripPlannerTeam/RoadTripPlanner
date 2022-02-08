package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.dtos.budget.BudgetRelationDTO;
import com.mycompany.roadtripplanner.dtos.itinerary.ItineraryRelationDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionRelationDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListRelationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageSaveDTO {
    private String name;
    private PositionRelationDTO position;
    private TodoListRelationDTO todoList;
    private BudgetRelationDTO budget;
    private Date date;
    private ItineraryRelationDTO itinerary;
}
