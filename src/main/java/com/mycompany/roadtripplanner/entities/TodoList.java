package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entité TodoList
 */

@Document
@AllArgsConstructor
@NoArgsConstructor
public class TodoList {

    @Id
    private String id;
    private String title;
    private String content;

}
