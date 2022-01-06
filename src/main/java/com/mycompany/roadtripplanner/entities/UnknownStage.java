package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Entité UnknownStage
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnknownStage {

    @Id
    private String id;
    @DBRef
    private Stage stage;
    @DBRef
    private List<InterestPoint> interestPoints;
}
