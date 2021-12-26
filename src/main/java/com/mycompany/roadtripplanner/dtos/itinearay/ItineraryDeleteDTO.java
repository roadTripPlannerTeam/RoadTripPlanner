package com.mycompany.roadtripplanner.dtos.itinearay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDeleteDTO {

    private String id;

}
