package com.mycompany.roadtripplanner.dtos.unknownstage;

import com.mycompany.roadtripplanner.dtos.position.PositionRelationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnknownStageSaveDTO {

    private String name;
    private PositionRelationDTO position;
    private String category;
}
