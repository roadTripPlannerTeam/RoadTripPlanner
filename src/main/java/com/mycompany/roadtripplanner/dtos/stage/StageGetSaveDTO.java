package com.mycompany.roadtripplanner.dtos.stage;

import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryRelationDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionRelationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageGetSaveDTO {
    private String id;
    private String name;
    private PositionRelationDTO position;
    private Date date;
    private ItineraryRelationDTO itinerary;
}
