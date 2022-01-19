package com.mycompany.roadtripplanner.dtos.interestpoint;

import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;

import com.mycompany.roadtripplanner.dtos.unknownstage.UnknownStageGetDTO;
import com.mycompany.roadtripplanner.dtos.unknownstage.UnknownStageGetSaveDTO;
import com.mycompany.roadtripplanner.entities.UnknownStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestPointDTO {

    private String id;
    private String name;
    private PositionGetDTO position;
    private String category;
    private String adresse;
    private UnknownStageGetDTO unknownStage;

}
