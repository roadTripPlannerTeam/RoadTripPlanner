package com.mycompany.roadtripplanner.dtos.position;

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
public class PositionDeleteDTO {
    private String id;
}
