package com.mycompany.roadtripplanner.dtos.todolist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListSaveDTO {

    private String title;
    private String content;

}
