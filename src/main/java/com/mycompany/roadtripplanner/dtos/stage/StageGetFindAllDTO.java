package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageGetFindAllDTO {
    private String id;
    private String name;
    private PositionGetDTO position;
}
