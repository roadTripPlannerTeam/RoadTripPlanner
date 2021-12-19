package com.mycompany.roadtripplanner.entities.stage;

import com.mycompany.roadtripplanner.entities.position.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entité Stage
 */

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Stage {
    @Id
    private String id;
    private String name;
    private Position position;
}
