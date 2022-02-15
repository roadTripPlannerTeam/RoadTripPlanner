package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.dtos.budget.BudgetDTO;
import com.mycompany.roadtripplanner.dtos.itinerary.ItineraryDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageGetDTO {
    private String id;
    private String name;
    private PositionGetDTO position;
    private TodoListDTO todoList;
    private BudgetDTO budget;
    private Date date;
    private ItineraryDTO itinerary;
}
