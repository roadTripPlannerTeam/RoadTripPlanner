package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entité Position
 */

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    private String id;
    private Long longitude;
    private Long latitude;
}
