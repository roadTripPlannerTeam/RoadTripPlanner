package com.mycompany.roadtripplanner.dtos.interestpoint;

import com.mycompany.roadtripplanner.entities.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestPointSaveDTO {
    private String name;
    private Position position;
    private String category;
    private String adresse;
}
