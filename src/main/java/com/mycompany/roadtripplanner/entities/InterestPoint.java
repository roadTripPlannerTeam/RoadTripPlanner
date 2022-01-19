package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Entité InterestPoint
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestPoint {

    @Id
    private String id;
    private String name;
    @DBRef
    private Position position;
    private String category;
    private String adresse;
    @DBRef
    private UnknownStage unknownStage ;

}
