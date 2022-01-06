package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryRelationDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionRelationDTO;
import com.mycompany.roadtripplanner.entities.Budget;
import com.mycompany.roadtripplanner.entities.TodoList;
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
    private TodoList todoList;
    private Budget budget;
    private Date date;
    private ItineraryRelationDTO itinerary;
}
