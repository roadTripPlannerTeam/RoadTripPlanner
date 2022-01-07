package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.dtos.budget.BudgetUpdateDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionUpdateDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListUpdateDTO;

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
    private TodoListUpdateDTO todoList;
    private BudgetUpdateDTO budget;
}
