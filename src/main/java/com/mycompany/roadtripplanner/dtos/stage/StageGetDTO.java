package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryRelationDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionRelationDTO;
import com.mycompany.roadtripplanner.entities.Budget;
import com.mycompany.roadtripplanner.entities.Position;
import com.mycompany.roadtripplanner.entities.TodoList;
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
    private TodoList todoList;
    private Budget budget;
    private Date date;
    private ItineraryDTO itinerary;
}
