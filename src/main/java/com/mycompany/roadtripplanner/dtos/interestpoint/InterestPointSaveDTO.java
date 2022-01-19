package com.mycompany.roadtripplanner.dtos.interestpoint;

import com.mycompany.roadtripplanner.dtos.position.PositionRelationDTO;
import com.mycompany.roadtripplanner.dtos.unknownstage.UnknownStageRelationDTO;
import com.mycompany.roadtripplanner.entities.Position;
import com.mycompany.roadtripplanner.entities.UnknownStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestPointSaveDTO {
    private String name;
    private PositionRelationDTO position;
    private String category;
    private String adresse;
    private UnknownStageRelationDTO unknownStage;
}
