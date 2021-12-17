package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Entité TodoList
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoList {

    @Id
    private String id;
    private String title;
    private String content;

}
