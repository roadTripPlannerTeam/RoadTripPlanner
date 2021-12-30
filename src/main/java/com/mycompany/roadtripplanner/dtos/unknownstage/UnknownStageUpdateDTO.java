package com.mycompany.roadtripplanner.dtos.unknownstage;

import com.mycompany.roadtripplanner.dtos.position.PositionUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnknownStageUpdateDTO {

    private String id;
    private String name;
    private PositionUpdateDTO position;
    private String category;

}
