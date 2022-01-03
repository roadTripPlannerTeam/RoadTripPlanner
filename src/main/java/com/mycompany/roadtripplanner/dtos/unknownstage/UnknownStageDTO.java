package com.mycompany.roadtripplanner.dtos.unknownstage;

import com.mycompany.roadtripplanner.dtos.position.PositionRelationDTO;
import com.mycompany.roadtripplanner.entities.InterestPoint;
import com.mycompany.roadtripplanner.entities.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnknownStageDTO {
    private String id;
    private Stage stage;
    private List<InterestPoint> interestPoints;
}
