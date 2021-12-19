package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.entities.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageDTO {
    private String id;
    private String name;
    private Position position;
}
