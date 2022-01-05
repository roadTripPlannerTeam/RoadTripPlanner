package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.dtos.position.PositionUpdateDTO;
import com.mycompany.roadtripplanner.entities.Budget;
import com.mycompany.roadtripplanner.entities.Position;
import com.mycompany.roadtripplanner.entities.TodoList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageUpdateDTO {
    private String id;
    private String name;
    private PositionUpdateDTO position;
    private TodoList todoList;
    private Budget budget;
}
