package com.mycompany.roadtripplanner.dtos.itinerary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryGetDTO {
    private String id;
    private String title;
    private String experienceFeedback;
    private float nbStars;

}
