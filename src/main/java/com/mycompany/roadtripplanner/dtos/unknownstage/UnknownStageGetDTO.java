package com.mycompany.roadtripplanner.dtos.unknownstage;

import com.mycompany.roadtripplanner.dtos.stage.StageRelationDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnknownStageGetDTO {
    private String id;
    private StageUpdateDTO stage;

}
