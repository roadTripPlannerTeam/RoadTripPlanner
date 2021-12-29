package com.mycompany.roadtripplanner.dtos.unknownstage;

import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnknownStageDTO {

    private String id;
    private String name;
    private PositionGetDTO position;
    private String category;

}
