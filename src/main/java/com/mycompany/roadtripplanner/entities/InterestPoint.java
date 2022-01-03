package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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
    private Position position;
    private String category;
    private String adresse;

}
