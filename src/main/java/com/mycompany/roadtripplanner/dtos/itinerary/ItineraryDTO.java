package com.mycompany.roadtripplanner.dtos.itinerary;

import com.mycompany.roadtripplanner.dtos.budget.BudgetDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentGetDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageGetFindAllDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListDTO;
import com.mycompany.roadtripplanner.dtos.user.UserGetSaveDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;


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
    private UserGetSaveDTO user;
    private List<CommentGetDTO> comments;
    private Map<Date, StageGetFindAllDTO> stages ;


}
