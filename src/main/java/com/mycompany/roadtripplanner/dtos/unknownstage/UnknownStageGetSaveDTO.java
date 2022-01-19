package com.mycompany.roadtripplanner.dtos.unknownstage;

import com.mycompany.roadtripplanner.dtos.stage.StageGetDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageRelationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnknownStageGetSaveDTO {
    private String id;
    private StageRelationDTO stage;
}
