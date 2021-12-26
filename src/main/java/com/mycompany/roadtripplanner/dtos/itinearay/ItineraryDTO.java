package com.mycompany.roadtripplanner.dtos.itinearay;

import com.mycompany.roadtripplanner.dtos.budget.BudgetDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListDTO;
import com.mycompany.roadtripplanner.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDTO {
    private String id;
    private String title;
    private String experienceFeedback;
    private float nbStars;
    private TodoListDTO todoList;
    private BudgetDTO budget;
    private List<CommentDTO> comments;
//    private Map<>;
}
