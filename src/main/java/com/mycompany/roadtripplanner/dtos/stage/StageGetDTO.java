package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionRelationDTO;
import com.mycompany.roadtripplanner.entities.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageGetDTO {
    private String id;
    private String name;
    private PositionGetDTO position;
}
